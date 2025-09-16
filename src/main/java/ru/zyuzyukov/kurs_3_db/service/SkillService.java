package ru.zyuzyukov.kurs_3_db.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.zyuzyukov.kurs_3_db.entity.Skill;
import ru.zyuzyukov.kurs_3_db.entity.Worker;
import ru.zyuzyukov.kurs_3_db.repositories.SkillRepository;

import java.util.List;
import java.util.UUID;
@Service
public class SkillService extends BaseService<Skill> {
    private final SkillRepository skillRepository;

    public SkillService(SkillRepository repository) {
        super(repository);
        this.skillRepository = repository;
    }
    public List<Skill> findAllById(List<UUID> vacancySkills) {
        return skillRepository.findAllById(vacancySkills);
    }
}
