package ru.zyuzyukov.kurs_3_db.mapper;

import org.springframework.stereotype.Component;
import ru.zyuzyukov.kurs_3_db.dto.EmployerDto;
import ru.zyuzyukov.kurs_3_db.dto.EmploymentDto;
import ru.zyuzyukov.kurs_3_db.entity.Employer;
import ru.zyuzyukov.kurs_3_db.entity.Employment;
import ru.zyuzyukov.kurs_3_db.entity.Vacancy;

import java.util.ArrayList;

@Component
public class EmploymentMapper implements Mapper<EmploymentDto, Employment> {

    @Override
    public EmploymentDto toDto(Employment entity) {
        return null;
    }

    @Override
    public Employment toCreateEntity(EmploymentDto dto) {
        return null;
    }
}
