package ru.zyuzyukov.kurs_3_db.controllers.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zyuzyukov.kurs_3_db.controllers.rest.BaseController;
import ru.zyuzyukov.kurs_3_db.dto.EmployerDto;
import ru.zyuzyukov.kurs_3_db.dto.EmploymentDto;
import ru.zyuzyukov.kurs_3_db.entity.Employer;
import ru.zyuzyukov.kurs_3_db.entity.Employment;
import ru.zyuzyukov.kurs_3_db.mapper.EmployerMapper;
import ru.zyuzyukov.kurs_3_db.mapper.EmploymentMapper;
import ru.zyuzyukov.kurs_3_db.service.BaseService;

@Controller("employmentViewController")
@RequestMapping("/view/employment")
public class EmploymentController  {

}
