package ru.zyuzyukov.kurs_3_db.mapper;

import org.springframework.stereotype.Component;
import ru.zyuzyukov.kurs_3_db.dto.EmployerDto;
import ru.zyuzyukov.kurs_3_db.db.entity.Employer;
import ru.zyuzyukov.kurs_3_db.db.entity.Vacancy;
import ru.zyuzyukov.kurs_3_db.db.service.VacancyService;

import java.util.List;

@Component
public class EmployerMapper implements Mapper<EmployerDto, Employer> {

    private final VacancyService vacancyService;

    public EmployerMapper(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @Override
    public EmployerDto toDto(Employer entity) {
        return new EmployerDto(
                entity.getId(),
                entity.getName(),
                entity.getVacancyList()
                        .stream()
                        .map(Vacancy::getId)
                        .toList(),
                entity.getActive());
    }

    @Override
    public Employer toCreateEntity(EmployerDto dto) {
        List<Vacancy> vacancyList = vacancyService.findAllById(dto.getVacancyList());
        return new Employer(
                dto.getId(),
                dto.getName(),
                dto.getActive(),
                vacancyList

        );
    }
}
