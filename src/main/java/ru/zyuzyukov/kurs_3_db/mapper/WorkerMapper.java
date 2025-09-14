package ru.zyuzyukov.kurs_3_db.mapper;

import org.springframework.stereotype.Component;
import ru.zyuzyukov.kurs_3_db.dto.WorkerDto;
import ru.zyuzyukov.kurs_3_db.entity.Worker;
@Component
public class WorkerMapper implements  Mapper<WorkerDto, Worker> {
    @Override
    public WorkerDto toDto(Worker entity) {
        return null;
    }

    @Override
    public Worker toCreateEntity(WorkerDto dto) {
        return null;
    }
}
