package ru.zyuzyukov.kurs_3_db.mapper;

import org.springframework.stereotype.Component;
import ru.zyuzyukov.kurs_3_db.dto.EmploymentDto;
import ru.zyuzyukov.kurs_3_db.entity.Employment;
import ru.zyuzyukov.kurs_3_db.entity.Vacancy;
import ru.zyuzyukov.kurs_3_db.entity.Worker;
import ru.zyuzyukov.kurs_3_db.service.BaseService;

import java.time.LocalDateTime;

@Component
public class EmploymentMapper implements Mapper<EmploymentDto, Employment> {

    private final BaseService<Vacancy> vacancyService;
    private final BaseService<Worker> workerService;

    public EmploymentMapper(BaseService<Vacancy> vacancyService, BaseService<Worker> workerService) {
        this.vacancyService = vacancyService;
        this.workerService = workerService;
    }

    @Override
    public EmploymentDto toDto(Employment entity) {
        return new EmploymentDto(
                entity.getId(),
                entity.getWorker().getId(),
                entity.getVacancy().getId(),
                entity.getDate_open(),
                entity.getDate_closed()
        );
    }

    @Override
    public Employment toCreateEntity(EmploymentDto dto) {
        Worker worker = workerService.findById(dto.getWorker_id()).orElseThrow(() -> new IllegalArgumentException("worker not found"));
        Vacancy vacancy = vacancyService.findById(dto.getVacancy_id()).orElseThrow(() -> new IllegalArgumentException("vacancy not found"));

        return new Employment(
                dto.getId(),
                worker,
                vacancy,
                dto.getDate_close(),
               LocalDateTime.now()

        );
    }
}
