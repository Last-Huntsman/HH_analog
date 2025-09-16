package ru.zyuzyukov.kurs_3_db.controllers.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zyuzyukov.kurs_3_db.dto.SkillDto;
import ru.zyuzyukov.kurs_3_db.entity.Skill;
import ru.zyuzyukov.kurs_3_db.mapper.SkillMapper;
import ru.zyuzyukov.kurs_3_db.service.BaseService;
import ru.zyuzyukov.kurs_3_db.service.SkillService;

@RestController
@RequestMapping("/skill")
public class SkillController extends BaseController<SkillDto,Skill> {
    protected SkillController(SkillService baseService, SkillMapper skillMapper) {
        super(baseService,skillMapper);
    }
}
