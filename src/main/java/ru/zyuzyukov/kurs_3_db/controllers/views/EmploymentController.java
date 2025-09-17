package ru.zyuzyukov.kurs_3_db.controllers.views;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zyuzyukov.kurs_3_db.controllers.rest.BaseController;
import ru.zyuzyukov.kurs_3_db.dto.EmployerDto;
import ru.zyuzyukov.kurs_3_db.dto.EmploymentDto;
import ru.zyuzyukov.kurs_3_db.entity.Employer;
import ru.zyuzyukov.kurs_3_db.entity.Employment;
import ru.zyuzyukov.kurs_3_db.mapper.EmployerMapper;
import ru.zyuzyukov.kurs_3_db.mapper.EmploymentMapper;
import ru.zyuzyukov.kurs_3_db.service.BaseService;
import ru.zyuzyukov.kurs_3_db.service.EmployerService;
import ru.zyuzyukov.kurs_3_db.service.EmploymentService;

import java.util.UUID;

@Controller("employmentViewController")
@RequestMapping("/view/employment")
public class EmploymentController  {
    private final EmploymentService employerService;
    private final EmploymentService mapper;

    public EmploymentController(EmploymentService employerService, EmploymentService mapper) {
        this.employerService = employerService;
        this.mapper = mapper;
    }




}
