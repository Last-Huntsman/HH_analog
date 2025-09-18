package ru.zyuzyukov.kurs_3_db.controllers.views;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.zyuzyukov.kurs_3_db.entity.Employment;
import ru.zyuzyukov.kurs_3_db.entity.Vacancy;
import ru.zyuzyukov.kurs_3_db.entity.Worker;
import ru.zyuzyukov.kurs_3_db.service.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller("EmploymentController")
@RequestMapping("/view/employment")
public class EmploymentController {
    private final EmploymentService employmentService;
    private final WorkerService workerService;
    private final VacancyService vacancyService;

    public EmploymentController(EmploymentService employmentService, WorkerService workerService, VacancyService vacancyService) {
        this.employmentService = employmentService;
        this.workerService = workerService;
        this.vacancyService = vacancyService;
    }

    // список стажа работника
    @GetMapping("/{workerId}")
    public String listEmployments(@PathVariable UUID workerId,
                                  @PageableDefault(size = 10, sort = "id") Pageable pageable,
                                  Model model) {

        Worker worker = workerService.findById(workerId).orElseThrow(
                ()-> new IllegalArgumentException("worker not found")
        );

        // все стажи по работнику
        List<Employment> employments = employmentService.findByWorkerId(workerId);

        // вакансии для выбора (с пагинацией)
        Page<Vacancy> allVacancies = vacancyService.findAll(pageable);

        model.addAttribute("worker", worker);
        model.addAttribute("employments", employments);
        model.addAttribute("vacanciesPage", allVacancies);

        return "employment/worker_employment";
    }

    // добавление стажа
    @PostMapping("/{workerId}/add")
    public String addEmployment(@PathVariable UUID workerId,
                                @RequestParam UUID vacancyId,
                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

        employmentService.addEmployment(workerId, vacancyId, start, end);
        return "redirect:/view/employment/" + workerId;
    }

    // удаление стажа
    @PostMapping("/{workerId}/remove")
    public String removeEmployment(@PathVariable UUID workerId,
                                   @RequestParam UUID employmentId) {
        employmentService.removeEmployment(employmentId);
        return "redirect:/view/employment/" + workerId;
    }
}

