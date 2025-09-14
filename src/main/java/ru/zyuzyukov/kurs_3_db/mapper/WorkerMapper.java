package ru.zyuzyukov.kurs_3_db.mapper;

import org.springframework.stereotype.Component;
import ru.zyuzyukov.kurs_3_db.dto.WorkerDto;
import ru.zyuzyukov.kurs_3_db.entity.Employer;
import ru.zyuzyukov.kurs_3_db.entity.Skill;
import ru.zyuzyukov.kurs_3_db.entity.Worker;
import ru.zyuzyukov.kurs_3_db.service.BaseService;

import java.util.List;

@Component
public class WorkerMapper implements  Mapper<WorkerDto, Worker> {
    private final BaseService<Skill> skillService;

    public WorkerMapper(BaseService<Skill> skillService) {
        this.skillService = skillService;
    }


    @Override
    public WorkerDto toDto(Worker entity) {
        return new WorkerDto(
                entity.getId(),
                entity.getName(),
                entity.getExperience(),
                entity.getWorkerSkills().stream().map(Skill::getId).toList()
        );
    }

    @Override
    public Worker toCreateEntity(WorkerDto dto) {
        List<Skill> skills = skillService.findAllById(dto.getWorkerSkillsId());
        return new Worker(
                null,
                dto.getName(),
                dto.getExperience(),
                skills
        );
    }
}
