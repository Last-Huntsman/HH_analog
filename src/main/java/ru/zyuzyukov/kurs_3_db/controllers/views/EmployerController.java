package ru.zyuzyukov.kurs_3_db.controllers.views;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zyuzyukov.kurs_3_db.dto.EmployerDto;
import ru.zyuzyukov.kurs_3_db.entity.Employer;
import ru.zyuzyukov.kurs_3_db.mapper.EmployerMapper;
import ru.zyuzyukov.kurs_3_db.service.BaseService;

import java.util.UUID;
import java.util.function.Supplier;

@Controller("employerViewController")
@RequestMapping("/view/employer")
public class EmployerController extends BaseViewController<EmployerDto, Employer> {
    public EmployerController(BaseService<Employer> baseService, EmployerMapper mapper) {
        super(baseService,
                mapper,
                 EmployerDto::new,
                "employer",
                "employers");
    }

}
