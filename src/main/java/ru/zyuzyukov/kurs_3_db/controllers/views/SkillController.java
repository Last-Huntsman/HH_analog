package ru.zyuzyukov.kurs_3_db.controllers.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zyuzyukov.kurs_3_db.controllers.rest.BaseController;
import ru.zyuzyukov.kurs_3_db.dto.EmploymentDto;
import ru.zyuzyukov.kurs_3_db.dto.SkillDto;
import ru.zyuzyukov.kurs_3_db.entity.Skill;
import ru.zyuzyukov.kurs_3_db.mapper.SkillMapper;
import ru.zyuzyukov.kurs_3_db.service.BaseService;
import ru.zyuzyukov.kurs_3_db.service.SkillService;

@Controller("skillViewController")
@RequestMapping("/view/skill")
public class SkillController extends BaseViewController<SkillDto,Skill> {
    private final SkillService skillService;
    protected SkillController(SkillService baseService, SkillMapper mapper) {
        super(baseService,
                mapper,
                SkillDto::new,
                "skill",
                "skills");
        this.skillService = baseService;
    }
}
