package ru.zyuzyukov.kurs_3_db.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zyuzyukov.kurs_3_db.db.entity.Vacancy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Data
@NoArgsConstructor
public class SkillDtoForVacancy extends SkillDto {
    public SkillDtoForVacancy(UUID id, String name,List<Vacancy> vacancies) {
        super(id,name);
        this.vacancies = vacancies;
    }
    private List<Vacancy> vacancies = new ArrayList<>();


}
