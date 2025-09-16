package ru.zyuzyukov.kurs_3_db.controllers.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zyuzyukov.kurs_3_db.controllers.rest.BaseController;
import ru.zyuzyukov.kurs_3_db.dto.EmploymentDto;
import ru.zyuzyukov.kurs_3_db.dto.WorkerDto;
import ru.zyuzyukov.kurs_3_db.entity.Worker;
import ru.zyuzyukov.kurs_3_db.mapper.WorkerMapper;
import ru.zyuzyukov.kurs_3_db.service.BaseService;

@Controller("workerViewController")
@RequestMapping("/view/worker")
public class WorkerController extends BaseViewController<WorkerDto,Worker> {
    protected WorkerController(BaseService<Worker> baseService, WorkerMapper    mapper) {
        super(baseService,
                mapper,
                WorkerDto::new,
                "worker",
                "workers");
    }
}
