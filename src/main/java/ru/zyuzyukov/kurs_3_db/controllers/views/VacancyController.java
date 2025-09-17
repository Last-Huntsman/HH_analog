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
import ru.zyuzyukov.kurs_3_db.entity.Employer;
import ru.zyuzyukov.kurs_3_db.entity.Skill;
import ru.zyuzyukov.kurs_3_db.entity.Vacancy;
import ru.zyuzyukov.kurs_3_db.mapper.SkillMapper;
import ru.zyuzyukov.kurs_3_db.mapper.VacancyMapper;
import ru.zyuzyukov.kurs_3_db.service.EmployerService;
import ru.zyuzyukov.kurs_3_db.service.SkillService;
import ru.zyuzyukov.kurs_3_db.service.VacancyService;

import java.util.UUID;

@Controller("vacancyViewController")
@RequestMapping("/view/vacancy")
public class VacancyController {
    private final VacancyService vacancyService;
    private final VacancyMapper vacancyMapper;
    private final EmployerService employerService;
    protected VacancyController(VacancyService vacancyService, VacancyMapper vacancyMapper, EmployerService employerService) {
        this.vacancyService = vacancyService;
        this.vacancyMapper = vacancyMapper;

        this.employerService = employerService;
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

        Vacancy vacancy = vacancyMapper.toCreateEntity(vacancyDto);
        vacancyService.save(vacancy);

        return "redirect:/view/vacancy/" + vacancy.getEmployer().getId();
    }

    @GetMapping("/edit/{id}")
    public String showEditVacancyForm(@PathVariable("id") UUID id, Model model) {
        Vacancy vacancy = vacancyService.findById(id).orElseThrow(EntityNotFoundException::new);
        VacancyDto dto = vacancyMapper.toDto(vacancy);
        model.addAttribute("vacancyDto", dto);
        return "vacancy/edit";
    }

    @PostMapping("/edit")
    public String update(@Valid @ModelAttribute VacancyDto vacancyDto,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "vacancy/edit";
        }

        Vacancy vacancy = vacancyMapper.toCreateEntity(vacancyDto);
        vacancyService.update(vacancy);

        return "redirect:/view/vacancy/" + vacancy.getEmployer().getId();
    }
    /** вакансии конкретного работодателя */
    @GetMapping("/{employerId}")
    public String listVacanciesForEmployer(@PageableDefault(size = 10, sort = "id") Pageable pageable,
                                           Model model,
                                           @PathVariable("employerId") UUID id) {
        Employer employer = employerService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employer not found"));
        Page<Vacancy> vacancies = vacancyService.findAllByEmployerId(id, pageable);
        Page<VacancyDto> vacancyDtos = vacancies.map(vacancyMapper::toDto);
        model.addAttribute("vacancies", vacancyDtos);
        model.addAttribute("page", vacancies);
        model.addAttribute("employer", employer);
        return "vacancy/employer_vacancies";
    }


}
