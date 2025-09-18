package ru.zyuzyukov.kurs_3_db.mapper;

import org.springframework.stereotype.Component;
import ru.zyuzyukov.kurs_3_db.dto.WorkerDto;
import ru.zyuzyukov.kurs_3_db.db.entity.Employment;
import ru.zyuzyukov.kurs_3_db.db.entity.Skill;
import ru.zyuzyukov.kurs_3_db.db.entity.Worker;
import ru.zyuzyukov.kurs_3_db.db.service.EmploymentService;
import ru.zyuzyukov.kurs_3_db.db.service.SkillService;

import java.util.List;

@Component
public class WorkerMapper implements  Mapper<WorkerDto, Worker> {
    private final SkillService skillService;
    private final EmploymentService employmentService;

    public WorkerMapper(SkillService skillService, EmploymentService employmentService) {
        this.skillService = skillService;
        this.employmentService = employmentService;
    }


    @Override
    public WorkerDto toDto(Worker entity) {
        return new WorkerDto(
                entity.getId(),
                entity.getName(),
                entity.getWorkerSkills().stream().map(Skill::getId).toList(),
                entity.getEmployments().stream().map(Employment::getId).toList()
        );
    }

    @Override
    public Worker toCreateEntity(WorkerDto dto) {
        List<Skill> skills = skillService.findByWorkerId(dto.getId());
        List<Employment> employments = employmentService.findByWorkerId(dto.getId());
        return new Worker(
                dto.getId(),
                dto.getName(),
                employments,
                skills

        );
    }
}
