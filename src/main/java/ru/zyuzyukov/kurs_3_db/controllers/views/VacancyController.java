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
import ru.zyuzyukov.kurs_3_db.dto.SkillDtoForVacancy;
import ru.zyuzyukov.kurs_3_db.dto.VacancyDto;
import ru.zyuzyukov.kurs_3_db.entity.Skill;
import ru.zyuzyukov.kurs_3_db.entity.Vacancy;
import ru.zyuzyukov.kurs_3_db.mapper.SkillMapper;
import ru.zyuzyukov.kurs_3_db.mapper.VacancyMapper;
import ru.zyuzyukov.kurs_3_db.service.SkillService;
import ru.zyuzyukov.kurs_3_db.service.VacancyService;

import java.util.List;
import java.util.UUID;

@Controller("vacancyViewController")
@RequestMapping("/view/vacancy")
public class VacancyController {
    private final VacancyService service;
    private final VacancyMapper mapper;
    private final SkillService skillService;
    private final SkillMapper skillMapper;

    protected VacancyController(VacancyService service, VacancyMapper mapper, SkillService skillService, SkillMapper skillMapper) {
        this.service = service;
        this.mapper = mapper;
        this.skillService = skillService;

        this.skillMapper = skillMapper;
    }

    @GetMapping("/create/{id}")
    public String showCreateVacancyForm(@PathVariable("id") UUID employerId, Model model) {
        VacancyDto vacancy = new VacancyDto();
        vacancy.setEmployerId(employerId);
        model.addAttribute("vacancyDto", vacancy);
        return "vacancy/create";
    }

    @PostMapping("/create")
    public String createVacancy(@Valid @ModelAttribute VacancyDto vacancyDto,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create";
        }

        if (vacancyDto.getEmployerId() == null) {
            throw new IllegalArgumentException("Employer ID must not be null");
        }

        Vacancy vacancy = mapper.toCreateEntity(vacancyDto);
        service.save(vacancy);

        return "redirect:/view/employer/" + vacancyDto.getEmployerId() + "/vacancies";
    }

    @GetMapping("/edit/{id}")
    public String showEditVacancyForm(@PathVariable("id") UUID id, Model model) {
        Vacancy vacancy = service.findById(id).orElseThrow(EntityNotFoundException::new);
        VacancyDto dto = mapper.toDto(vacancy);
        model.addAttribute("vacancyDto", dto);
        return "vacancy/edit";
    }

    @PostMapping("/edit")
    public String update(@Valid @ModelAttribute VacancyDto vacancyDto,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "vacancy/edit";
        }

        Vacancy vacancy = mapper.toCreateEntity(vacancyDto);
        service.update(vacancy);
        return "redirect:/view/employer/" + vacancy.getEmployer().getId() + "/vacancies";
    }
    @GetMapping("{employerId}/vacancies/{vacancyId}/skills")
    public String listSkillsForVacancy(@PageableDefault(size = 10, sort = "id") Pageable pageable,
                                       Model model,
                                       @PathVariable("employerId") UUID employerId,
                                       @PathVariable("vacancyId") UUID vacancyId){
        Vacancy vacancy = service.findById(vacancyId).orElseThrow(EntityNotFoundException::new);

        Page<Skill> skills = skillService.findByVacancyId(vacancyId,pageable);
        Page<SkillDtoForVacancy> skillDtoForVacancies = skills.map(skillMapper::toVacancyDto);
        model.addAttribute("vacancy", vacancy);
        model.addAttribute("skills", skillDtoForVacancies.getContent());
        model.addAttribute("pageable", pageable);
        return "employer/vacancy_skills";
    }


}
