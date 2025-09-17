package ru.zyuzyukov.kurs_3_db.controllers.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.zyuzyukov.kurs_3_db.mapper.SkillMapper;
import ru.zyuzyukov.kurs_3_db.service.SkillService;

@Controller("skillViewController")
@RequestMapping("/view/skill")
public class SkillController {
    private final SkillService skillService;
    private final SkillMapper skillMapper;

    protected SkillController(SkillService baseService, SkillMapper mapper) {
        this.skillMapper = mapper;
        this.skillService = baseService;
    }
}
