package ru.zyuzyukov.kurs_3_db.controllers.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zyuzyukov.kurs_3_db.dto.EmploymentDto;
import ru.zyuzyukov.kurs_3_db.entity.Employment;
import ru.zyuzyukov.kurs_3_db.mapper.EmploymentMapper;
import ru.zyuzyukov.kurs_3_db.service.BaseService;
import ru.zyuzyukov.kurs_3_db.service.EmploymentService;

@RestController
@RequestMapping("/employment")
public class EmploymentController extends BaseController<EmploymentDto,Employment> {
    protected EmploymentController(EmploymentService baseService, EmploymentMapper mapper) {
        super(baseService,mapper);
    }
}
