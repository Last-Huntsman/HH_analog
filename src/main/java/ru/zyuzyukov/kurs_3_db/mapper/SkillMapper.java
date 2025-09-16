package ru.zyuzyukov.kurs_3_db.mapper;

import org.springframework.stereotype.Component;
import ru.zyuzyukov.kurs_3_db.dto.SkillDto;
import ru.zyuzyukov.kurs_3_db.entity.Skill;
@Component
public class SkillMapper implements Mapper<SkillDto, Skill> {
    @Override
    public SkillDto toDto(Skill entity) {
        return new SkillDto(entity.getId(), entity.getName());
    }

    @Override
    public Skill toCreateEntity(SkillDto dto) {
        return new Skill(dto.getId(), dto.getName());
    }
}
