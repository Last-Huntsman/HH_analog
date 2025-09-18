package ru.zyuzyukov.kurs_3_db.controllers.views;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zyuzyukov.kurs_3_db.dto.SkillDto;
import ru.zyuzyukov.kurs_3_db.dto.SkillDtoForVacancy;
import ru.zyuzyukov.kurs_3_db.dto.SkillDtoForWorker;
import ru.zyuzyukov.kurs_3_db.entity.Skill;
import ru.zyuzyukov.kurs_3_db.entity.Vacancy;
import ru.zyuzyukov.kurs_3_db.entity.Worker;
import ru.zyuzyukov.kurs_3_db.mapper.SkillMapper;
import ru.zyuzyukov.kurs_3_db.service.SkillService;
import ru.zyuzyukov.kurs_3_db.service.VacancyService;
import ru.zyuzyukov.kurs_3_db.service.WorkerService;

import java.util.List;
import java.util.UUID;

@Controller("skillViewController")
@RequestMapping("/view/skill")
public class SkillController {
    private final SkillService skillService;
    private final SkillMapper skillMapper;
    private final VacancyService vacancyService;
    private final WorkerService workerService;

    protected SkillController(SkillService baseService, SkillMapper mapper, VacancyService vacancyService, WorkerService workerService) {
        this.skillMapper = mapper;
        this.skillService = baseService;
        this.vacancyService = vacancyService;
        this.workerService = workerService;
    }

    @GetMapping("{employerId}/{vacancyId}")
    public String listSkillsForVacancy(
            @PageableDefault(size = 10, sort = "name") Pageable pageable,
            Model model,
            @PathVariable("employerId") UUID employerId,
            @PathVariable("vacancyId") UUID vacancyId) {

        Vacancy vacancy = vacancyService.findById(vacancyId)
                .orElseThrow(EntityNotFoundException::new);


        List<SkillDtoForVacancy> vacancySkills = vacancy.getVacancySkills().stream()
                .map(skillMapper::toVacancyDto)
                .toList();


        Page<Skill> allSkillsPage = skillService.findAll(pageable);


        List<UUID> vacancySkillIds = vacancy.getVacancySkills().stream()
                .map(Skill::getId)
                .toList();


        List<Skill> filteredSkills = allSkillsPage.getContent().stream()
                .filter(skill -> !vacancySkillIds.contains(skill.getId()))
                .toList();


        Page<Skill> otherSkillsPage = new org.springframework.data.domain.PageImpl<>(
                filteredSkills,
                pageable,
                allSkillsPage.getTotalElements() - vacancySkillIds.size()
        );


        model.addAttribute("vacancy", vacancy);
        model.addAttribute("vacancySkills", vacancySkills);
        model.addAttribute("otherSkillsPage", otherSkillsPage);

        return "skill/vacancy_skills";
    }


    @PostMapping("{vacancyId}/remove")
    public String removeSkillsForVacancy(
            @PathVariable UUID vacancyId,
            @RequestParam UUID skillId) {

        vacancyService.removeSkillById(vacancyId, skillId);
        UUID employerId = vacancyService.findById(vacancyId)
                .orElseThrow(EntityNotFoundException::new)
                .getEmployer().getId();

        return "redirect:/view/skill/" + employerId + "/" + vacancyId;
    }


    @PostMapping("{vacancyId}/skills/add")
    public String addSkillsForVacancy(@PathVariable UUID vacancyId,
                                      @RequestParam UUID skillId) {
        vacancyService.addSkill(vacancyId, skillId);
        UUID employerId = vacancyService.findById(vacancyId)
                .orElseThrow(EntityNotFoundException::new)
                .getEmployer().getId();

        return "redirect:/view/skill/" + employerId + "/" + vacancyId;
    }
//-------------------------------------------
    @GetMapping("{workerId}")
    public String listSkillsForWorker(
            @PageableDefault(size = 10, sort = "name") Pageable pageable,
            Model model,
            @PathVariable("workerId") UUID workerId) {

        Worker worker = workerService.findById(workerId)
                .orElseThrow(EntityNotFoundException::new);


        List<SkillDtoForWorker> workerSkills = worker.getWorkerSkills().stream()
                .map(skillMapper::toWorkerDto)
                .toList();


        Page<Skill> allSkillsPage = skillService.findAll(pageable);


        List<UUID> workerSkillsId = worker.getWorkerSkills().stream()
                .map(Skill::getId)
                .toList();


        List<Skill> filteredSkills = allSkillsPage.getContent().stream()
                .filter(skill -> !workerSkillsId.contains(skill.getId()))
                .toList();


        Page<Skill> otherSkillsPage = new org.springframework.data.domain.PageImpl<>(
                filteredSkills,
                pageable,
                allSkillsPage.getTotalElements() - workerSkillsId.size()
        );


        model.addAttribute("worker", worker);
        model.addAttribute("workerSkills", workerSkills);
        model.addAttribute("otherSkillsPage", otherSkillsPage);

        return "skill/worker_skills";
    }


    @PostMapping("{workerId}/remove/worker")
    public String removeSkillsForWorker(
            @PathVariable UUID workerId,
            @RequestParam UUID skillId) {

        workerService.removeSkillById(workerId, skillId);


        return "redirect:/view/skill/" + workerId ;
    }


    @PostMapping("{workerId}/skills/add/worker")
    public String addSkillsForWorker(@PathVariable UUID workerId,
                                      @RequestParam UUID skillId) {
       workerService.addSkill(workerId, skillId);
        return "redirect:/view/skill/" + workerId ;
    }


    //----------------------------------------------------------------------------
    @GetMapping
    public String list(@PageableDefault(size = 10, sort = "id") Pageable pageable,
                       Model model) {
        Page<SkillDto> page = skillService.findAll(pageable)
                .map(skillMapper::createDto);
        model.addAttribute("skills", page.getContent());
        model.addAttribute("page", page);
        return "skill/list";
    }

    /**
     * форма создания
     */
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("skillDto", new SkillDto());
        return "skill/create";
    }

    /**
     * обработка создания
     */
    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("skillDto") SkillDto dto,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "skill/create";
        }
        Skill entity = skillMapper.createEntity(dto);
        skillService.save(entity);
        return "redirect:/view/skill";
    }

    // Форма редактирования навыка
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable UUID id, Model model) {
        Skill entity = skillService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Skill not found"));
        model.addAttribute("skillDto", skillMapper.createDto(entity));
        return "skill/edit";
    }

    // Обработка редактирования
    @PostMapping("/edit")
    public String update(@Valid @ModelAttribute("skillDto") SkillDto dto,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "skill/edit";
        }
        Skill entity = skillMapper.createEntity(dto);
        skillService.update(entity);
        return "redirect:/view/skill";
    }

    // Удаление навыка
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable UUID id) {
        skillService.delete(id);
        return "redirect:/view/skill";
    }


}
