package ru.zyuzyukov.kurs_3_db.controllers.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zyuzyukov.kurs_3_db.controllers.rest.BaseController;
import ru.zyuzyukov.kurs_3_db.dto.EmploymentDto;
import ru.zyuzyukov.kurs_3_db.dto.VacancyDto;
import ru.zyuzyukov.kurs_3_db.entity.Vacancy;
import ru.zyuzyukov.kurs_3_db.mapper.VacancyMapper;
import ru.zyuzyukov.kurs_3_db.service.BaseService;

@Controller("vacancyViewController")
@RequestMapping("/view/vacancy")
public class VacancyController extends BaseViewController<VacancyDto,Vacancy> {
    protected VacancyController(BaseService<Vacancy> baseService, VacancyMapper mapper) {
        super(baseService,
                mapper,
                VacancyDto::new,
                "vacancy",
                "vacancies");
    }
}
