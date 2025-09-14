package ru.zyuzyukov.kurs_3_db.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zyuzyukov.kurs_3_db.entity.Skill;
import ru.zyuzyukov.kurs_3_db.entity.Vacancy;
import ru.zyuzyukov.kurs_3_db.service.BaseService;

@RestController
@RequestMapping("/vacancy")
public class VacancyController extends BaseController<Vacancy> {
    protected VacancyController(BaseService<Vacancy> baseService) {
        super(baseService);
    }
}
