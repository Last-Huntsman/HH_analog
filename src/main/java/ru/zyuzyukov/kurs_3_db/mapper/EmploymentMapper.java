package ru.zyuzyukov.kurs_3_db.mapper;

import org.springframework.stereotype.Component;
import ru.zyuzyukov.kurs_3_db.dto.EmployerDto;
import ru.zyuzyukov.kurs_3_db.entity.Employer;
import ru.zyuzyukov.kurs_3_db.entity.Vacancy;

import java.util.ArrayList;
@Component
public class EmployerMapper implements Mapper<EmployerDto, Employer> {

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
        return new Employer(
                null,
                dto.getName(),
                new ArrayList<>()
        );
    }
}
