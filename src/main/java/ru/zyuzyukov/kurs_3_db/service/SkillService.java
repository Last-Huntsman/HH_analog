package ru.zyuzyukov.kurs_3_db.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.zyuzyukov.kurs_3_db.entity.Skill;
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

    public Page<Skill> findByVacancyId(UUID vacancyId, Pageable pageable) {
        return skillRepository.findByVacancies_Id(vacancyId, pageable);
    }

    public List<Skill> findByVacancyId(UUID vacancyId) {
        return skillRepository.findByVacancies_Id(vacancyId);
    }

    public List<Skill> findByWorkerId(UUID workerId) {
        return skillRepository.findByWorkers_Id(workerId);
    }
}
