package ru.zyuzyukov.kurs_3_db.mapper;

import org.springframework.stereotype.Component;
import ru.zyuzyukov.kurs_3_db.dto.SkillDto;
import ru.zyuzyukov.kurs_3_db.dto.SkillDtoForVacancy;
import ru.zyuzyukov.kurs_3_db.dto.SkillDtoForWorker;
import ru.zyuzyukov.kurs_3_db.entity.Skill;

import java.util.ArrayList;

@Component
public class SkillMapper {

    public SkillDtoForVacancy toVacancyDto(Skill entity) {
        return new SkillDtoForVacancy(
                entity.getId(),
                entity.getName(),
                entity.getVacancies()
        );
    }

    public SkillDtoForWorker toWorkerDto(Skill entity) {
        return new SkillDtoForWorker(
                entity.getId(),
                entity.getName(),
                entity.getWorkers()
        );
    }

    public Skill toEntity(SkillDto dto) {
        if (dto instanceof SkillDtoForVacancy vacancyDto) {
            return new Skill(
                    vacancyDto.getId(),
                    vacancyDto.getName(),
                    vacancyDto.getVacancies(),
                    new ArrayList<>()
            );
        } else if (dto instanceof SkillDtoForWorker workerDto) {
            return new Skill(
                    workerDto.getId(),
                    workerDto.getName(),
                    new ArrayList<>(),
                    workerDto.getWorkers()
            );
        }
        throw new IllegalArgumentException("Unsupported DTO type: " + dto.getClass());
    }
}
