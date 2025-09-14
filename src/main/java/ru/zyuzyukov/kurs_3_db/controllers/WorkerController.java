package ru.zyuzyukov.kurs_3_db.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zyuzyukov.kurs_3_db.entity.Skill;
import ru.zyuzyukov.kurs_3_db.entity.Worker;
import ru.zyuzyukov.kurs_3_db.service.BaseService;

@RestController
@RequestMapping("/worker")
public class WorkerController extends BaseController<Worker> {
    protected WorkerController(BaseService<Worker> baseService) {
        super(baseService);
    }
}
