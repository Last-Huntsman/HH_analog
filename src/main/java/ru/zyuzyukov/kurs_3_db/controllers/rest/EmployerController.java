package ru.zyuzyukov.kurs_3_db.controllers.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zyuzyukov.kurs_3_db.dto.EmployerDto;
import ru.zyuzyukov.kurs_3_db.entity.Employer;
import ru.zyuzyukov.kurs_3_db.mapper.EmployerMapper;
import ru.zyuzyukov.kurs_3_db.service.BaseService;
import ru.zyuzyukov.kurs_3_db.service.EmployerService;

@RestController
@RequestMapping("/employer")
public class EmployerController extends BaseController<EmployerDto,Employer> {
    protected EmployerController(EmployerService baseService, EmployerMapper mapper) {
        super(baseService, mapper);
    }
}
