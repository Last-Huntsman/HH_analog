package ru.zyuzyukov.kurs_3_db.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zyuzyukov.kurs_3_db.entity.Employer;
import ru.zyuzyukov.kurs_3_db.entity.Skill;
import ru.zyuzyukov.kurs_3_db.service.BaseService;

@RestController
@RequestMapping("/skill")
public class SkillController extends BaseController<Skill> {
    protected SkillController(BaseService<Skill> baseService) {
        super(baseService);
    }
}
