package ru.zyuzyukov.kurs_3_db.controllers.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zyuzyukov.kurs_3_db.dto.SkillDtoForVacancy;
import ru.zyuzyukov.kurs_3_db.entity.Skill;
import ru.zyuzyukov.kurs_3_db.mapper.SkillMapper;
import ru.zyuzyukov.kurs_3_db.service.SkillService;

//@RestController
//@RequestMapping("/skill")//TODO Поправить варибальность скиллов
//public class SkillController extends BaseController<SkillDtoForVacancy,Skill> {
//    protected SkillController(SkillService baseService, SkillMapper skillMapper) {
//        super(baseService,skillMapper);
//    }
//
//}
