package ru.zyuzyukov.kurs_3_db.mapper;

import org.springframework.stereotype.Component;
import ru.zyuzyukov.kurs_3_db.dto.VacancyDto;
import ru.zyuzyukov.kurs_3_db.entity.Vacancy;
@Component
public class VacancyMapper implements Mapper<VacancyDto, Vacancy> {
    @Override
    public VacancyDto toDto(Vacancy entity) {
        return null;
    }

    @Override
    public Vacancy toCreateEntity(VacancyDto dto) {
        return null;
    }
}
