package ru.zyuzyukov.kurs_3_db.controllers.views;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.zyuzyukov.kurs_3_db.dto.EmployerDto;
import ru.zyuzyukov.kurs_3_db.entity.Employer;
import ru.zyuzyukov.kurs_3_db.entity.Vacancy;
import ru.zyuzyukov.kurs_3_db.mapper.EmployerMapper;
import ru.zyuzyukov.kurs_3_db.service.EmployerService;
import ru.zyuzyukov.kurs_3_db.service.VacancyService;

import java.util.UUID;

@Controller("employerViewController")
@RequestMapping("/view/employer")

public class EmployerController extends BaseViewController<EmployerDto, Employer> {
    private final VacancyService vacancyService;
    private final EmployerService employerService;

    public EmployerController(EmployerService employerService, EmployerMapper mapper, VacancyService vacancyService) {
        super(employerService,
                mapper,
                EmployerDto::new,
                "employer",
                "employers");
        this.vacancyService = vacancyService;
        this.employerService = employerService;
    }

    @GetMapping("{id}/vacancies")
    public String listVacanciesForEmployer(@PageableDefault(size = 10, sort = "id") Pageable pageable,
                       Model model, @PathVariable UUID id) {
        Employer employer = employerService.findById(id).orElseThrow(() -> new IllegalArgumentException("Employer not found"));
        Page<Vacancy> vacancies = vacancyService.findAllByEmployerId(id, pageable);
        model.addAttribute("vacancies", vacancies.getContent());
        model.addAttribute("page", vacancies);
        model.addAttribute("id", id);
        model.addAttribute("employer", employer);


        return "employer/vacancies";
    }


}
