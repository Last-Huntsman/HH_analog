package ru.zyuzyukov.kurs_3_db.controllers.views;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.zyuzyukov.kurs_3_db.dto.SkillDtoForVacancy;
import ru.zyuzyukov.kurs_3_db.entity.Skill;
import ru.zyuzyukov.kurs_3_db.entity.Vacancy;
import ru.zyuzyukov.kurs_3_db.mapper.SkillMapper;
import ru.zyuzyukov.kurs_3_db.service.SkillService;
import ru.zyuzyukov.kurs_3_db.service.VacancyService;

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
    public String listSkillsForVacancy(@PageableDefault(size = 10, sort = "id") Pageable pageable,
                                       Model model,
                                       @PathVariable("employerId") UUID employerId,
                                       @PathVariable("vacancyId") UUID vacancyId){
        Vacancy vacancy = vacancyService.findById(vacancyId).orElseThrow(EntityNotFoundException::new);

        Page<Skill> skills = skillService.findByVacancyId(vacancyId,pageable);
        Page<SkillDtoForVacancy> skillDtoForVacancies = skills.map(skillMapper::toVacancyDto);
        model.addAttribute("vacancy", vacancy);
        model.addAttribute("skills", skillDtoForVacancies.getContent());
        model.addAttribute("pageable", pageable);
        return "skill/vacancy_skills";
    }
}
