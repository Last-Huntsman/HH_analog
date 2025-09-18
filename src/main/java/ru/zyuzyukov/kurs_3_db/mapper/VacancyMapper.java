package ru.zyuzyukov.kurs_3_db.mapper;

import org.springframework.stereotype.Component;
import ru.zyuzyukov.kurs_3_db.dto.VacancyDto;
import ru.zyuzyukov.kurs_3_db.db.entity.Employer;
import ru.zyuzyukov.kurs_3_db.db.entity.Skill;
import ru.zyuzyukov.kurs_3_db.db.entity.Vacancy;
import ru.zyuzyukov.kurs_3_db.db.service.EmployerService;
import ru.zyuzyukov.kurs_3_db.db.service.SkillService;

import java.util.List;


@Component
public class VacancyMapper implements Mapper<VacancyDto, Vacancy> {

    private final SkillService skillService;
    private final EmployerService employerService;

    public VacancyMapper(SkillService skillService, EmployerService employerService) {
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
        Employer employer = employerService.findById(dto.getEmployerId())
                .orElseThrow(() -> new IllegalArgumentException("employer not found"));

        // Проверяем существование всех переданных навыков
        List<Skill> skillsFromDb = skillService.findAll(dto.getVacancySkills());
        if (skillsFromDb.size() != dto.getVacancySkills().size()) {
            throw new IllegalArgumentException("Some skills not found");
        }

        return new Vacancy(
                dto.getId(),
                employer,
                dto.getSalary(),
                dto.getDescription(),
                dto.getPost(),
                dto.getActive(),
                skillsFromDb
        );
    }

}
