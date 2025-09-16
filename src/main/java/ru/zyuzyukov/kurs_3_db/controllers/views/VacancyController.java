package ru.zyuzyukov.kurs_3_db.controllers.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zyuzyukov.kurs_3_db.dto.VacancyDto;
import ru.zyuzyukov.kurs_3_db.entity.Vacancy;
import ru.zyuzyukov.kurs_3_db.mapper.VacancyMapper;
import ru.zyuzyukov.kurs_3_db.service.BaseService;

@RestController
@RequestMapping("/vacancy")
public class VacancyController extends BaseController<VacancyDto,Vacancy> {
    protected VacancyController(BaseService<Vacancy> baseService, VacancyMapper vacancyMapper) {
        super(baseService,vacancyMapper);
    }
}
