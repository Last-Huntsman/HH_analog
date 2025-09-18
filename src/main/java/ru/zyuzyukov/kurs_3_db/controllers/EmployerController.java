package ru.zyuzyukov.kurs_3_db.controllers;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zyuzyukov.kurs_3_db.dto.EmployerDto;
import ru.zyuzyukov.kurs_3_db.dto.VacancyDto;
import ru.zyuzyukov.kurs_3_db.entity.Employer;
import ru.zyuzyukov.kurs_3_db.entity.Vacancy;
import ru.zyuzyukov.kurs_3_db.mapper.EmployerMapper;
import ru.zyuzyukov.kurs_3_db.mapper.VacancyMapper;
import ru.zyuzyukov.kurs_3_db.service.EmployerService;
import ru.zyuzyukov.kurs_3_db.service.VacancyService;

import java.util.UUID;

@Controller("employerViewController")
@RequestMapping("/view/employer")
public class EmployerController {


    private final EmployerService employerService;
    private final EmployerMapper mapper;


    public EmployerController(EmployerService employerService,
                              EmployerMapper mapper) {
        this.mapper = mapper;
        this.employerService = employerService;
    }

    /** список работодателей */
    @GetMapping
    public String list(@PageableDefault(size = 10, sort = "id") Pageable pageable,
                       Model model) {
        Page<EmployerDto> page = employerService.findAll(pageable).map(mapper::toDto);
        model.addAttribute("employers", page.getContent());
        model.addAttribute("page", page);
        return "employer/list";
    }

    /** форма создания */
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("employerDto", new EmployerDto());
        return "employer/create";
    }

    /** обработка создания */
    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("employerDto") EmployerDto dto,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "employer/create";
        }
        Employer entity = mapper.toCreateEntity(dto);
        employerService.save(entity);
        return "redirect:/view/employer";
    }

    /** форма редактирования */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable UUID id, Model model) {
        Employer entity = employerService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employer not found"));
        model.addAttribute("employerDto", mapper.toDto(entity));
        return "employer/edit";
    }

    /** обработка редактирования */
    @PostMapping("/edit")
    public String update(@Valid @ModelAttribute("employerDto") EmployerDto dto,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "employer/edit";
        }
        Employer entity = mapper.toCreateEntity(dto);
        employerService.update(entity);
        return "redirect:/view/employer";
    }




}
