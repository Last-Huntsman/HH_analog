package ru.zyuzyukov.kurs_3_db.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.zyuzyukov.kurs_3_db.dto.EmployerDto;
import ru.zyuzyukov.kurs_3_db.entity.Employer;
import ru.zyuzyukov.kurs_3_db.mapper.EmployerMapper;
import ru.zyuzyukov.kurs_3_db.service.BaseService;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/view/employer")
public class EmployerController {

    private final BaseService<Employer> baseService;
    private final EmployerMapper mapper;

    public EmployerController(BaseService<Employer> baseService, EmployerMapper mapper) {
        this.baseService = baseService;
        this.mapper = mapper;
    }

    // 📋 Список работодателей с пагинацией
    @GetMapping
    public String listEmployers(
            @PageableDefault(size = 10, sort = "name") Pageable pageable,
            Model model
    ) {
        Page<EmployerDto> page = baseService.findAll(pageable).map(mapper::toDto);
        model.addAttribute("employers", page.getContent());
        model.addAttribute("page", page);
        return "employer/list";
    }

    // 🔹 Показать форму создания
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("employer", new EmployerDto());
        return "employer/create";
    }

    // 🔹 Обработка создания
    @PostMapping("/create")
    public String createEmployer(@Valid @ModelAttribute("employer") EmployerDto employerDto,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "employer/create";
        }
        baseService.save(mapper.toCreateEntity(employerDto));
        return "redirect:/view/employer";
    }

    // ✏️ Показать форму редактирования
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable UUID id, Model model) {
        Employer employer = baseService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employer not found"));
        model.addAttribute("employer", mapper.toDto(employer));
        return "employer/edit";
    }

    // ✏️ Обновление
    @PostMapping("/edit")
    public String updateEmployer(@Valid @ModelAttribute("employer") EmployerDto employerDto,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "employer/edit";
        }
        baseService.update(mapper.toCreateEntity(employerDto));
        return "redirect:/view/employer";
    }

    // ❌ Удаление
    @GetMapping("/delete/{id}")
    public String deleteEmployer(@PathVariable UUID id) {
        baseService.delete(id);
        return "redirect:/view/employer";
    }
}
