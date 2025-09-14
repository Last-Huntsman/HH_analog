package ru.zyuzyukov.kurs_3_db.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zyuzyukov.kurs_3_db.entity.Employer;
import ru.zyuzyukov.kurs_3_db.service.BaseService;

@RestController
@RequestMapping("/employer")
public class EmployerController extends BaseController<Employer> {
    protected EmployerController(BaseService<Employer> baseService) {
        super(baseService);
    }
}
