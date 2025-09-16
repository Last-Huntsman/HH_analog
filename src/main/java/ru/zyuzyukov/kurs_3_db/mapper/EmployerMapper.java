package ru.zyuzyukov.kurs_3_db.mapper;

import org.springframework.stereotype.Component;
import ru.zyuzyukov.kurs_3_db.dto.EmployerDto;
import ru.zyuzyukov.kurs_3_db.entity.Employer;
import ru.zyuzyukov.kurs_3_db.entity.Vacancy;
import ru.zyuzyukov.kurs_3_db.service.BaseService;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployerMapper implements Mapper<EmployerDto, Employer> {

    private final BaseService<Vacancy> vacancyService;

    public EmployerMapper(BaseService<Vacancy> vacancyService) {
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
                        .toList());
    }

    @Override
    public Employer toCreateEntity(EmployerDto dto) {
       List<Vacancy> vacancyList = vacancyService.findAllById(dto.getVacancyList());
        return new Employer(
                dto.getId(),
                dto.getName(),
               vacancyList
        );
    }
}
