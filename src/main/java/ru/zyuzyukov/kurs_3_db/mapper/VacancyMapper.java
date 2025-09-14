package ru.zyuzyukov.kurs_3_db.mapper;

import org.springframework.stereotype.Component;
import ru.zyuzyukov.kurs_3_db.dto.VacancyDto;
import ru.zyuzyukov.kurs_3_db.entity.Employer;
import ru.zyuzyukov.kurs_3_db.entity.Skill;
import ru.zyuzyukov.kurs_3_db.entity.Vacancy;
import ru.zyuzyukov.kurs_3_db.service.BaseService;

import java.util.List;


@Component

public class VacancyMapper implements Mapper<VacancyDto, Vacancy> {

    private final BaseService<Skill> skillService;
    private final BaseService<Employer> employerService;

    public VacancyMapper(BaseService<Skill> skillService, BaseService<Employer> employerService) {
        this.skillService = skillService;
        this.employerService = employerService;
    }

    @Override
    public VacancyDto toDto(Vacancy entity) {

        return new VacancyDto(
                entity.getId(),
                entity.getEmployer().getId(),
                entity.getSalary(),
                entity.getDescription(),
                entity.getPost(),
                entity.getActive(),
                entity.getVacancySkills().stream().map(Skill::getId).toList()
        );
    }

    @Override
    public Vacancy toCreateEntity(VacancyDto dto) {
        Employer employer = employerService.findById(dto.getId()).orElseThrow(() -> new IllegalArgumentException("employer not found"));
        List<Skill> skills = skillService.findAllById(dto.getVacancySkills());
        if (skills.size() != dto.getVacancySkills().size()) {
            throw new IllegalArgumentException("Some skills not found");
        }
        return new Vacancy(
                dto.getId(),
                employer,
                dto.getSalary(),
                dto.getDescription(),
                dto.getPost(),
                dto.getActive(),
                skills
        );
    }
}
