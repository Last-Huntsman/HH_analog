package ru.zyuzyukov.kurs_3_db.controllers.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zyuzyukov.kurs_3_db.dto.WorkerDto;
import ru.zyuzyukov.kurs_3_db.entity.Worker;
import ru.zyuzyukov.kurs_3_db.mapper.WorkerMapper;
import ru.zyuzyukov.kurs_3_db.service.BaseService;

@RestController
@RequestMapping("/worker")
public class WorkerController extends BaseController<WorkerDto,Worker> {
    protected WorkerController(BaseService<Worker> baseService, WorkerMapper    workerMapper) {
        super(baseService, workerMapper);
    }
}
