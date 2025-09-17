package ru.zyuzyukov.kurs_3_db.controllers.views;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.zyuzyukov.kurs_3_db.dto.SkillDtoForVacancy;
import ru.zyuzyukov.kurs_3_db.entity.Skill;
import ru.zyuzyukov.kurs_3_db.entity.Vacancy;
import ru.zyuzyukov.kurs_3_db.mapper.SkillMapper;
import ru.zyuzyukov.kurs_3_db.service.SkillService;
import ru.zyuzyukov.kurs_3_db.service.VacancyService;

import java.util.List;
import java.util.UUID;

@Controller("skillViewController")
@RequestMapping("/view/skill")
public class SkillController {
    private final SkillService skillService;
    private final SkillMapper skillMapper;

    private final VacancyService vacancyService;
    protected SkillController(SkillService baseService, SkillMapper mapper, VacancyService vacancyService) {
        this.skillMapper = mapper;
        this.skillService = baseService;
        this.vacancyService = vacancyService;
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

        return "redirect:/view/skill/" + employerId+"/"+ vacancyId;
    }


}
