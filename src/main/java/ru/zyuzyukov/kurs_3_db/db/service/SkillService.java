package ru.zyuzyukov.kurs_3_db.db.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.zyuzyukov.kurs_3_db.db.entity.Skill;
import ru.zyuzyukov.kurs_3_db.db.repositories.SkillRepository;
import ru.zyuzyukov.kurs_3_db.db.repositories.VacancyRepository;

import java.util.List;
import java.util.UUID;

@Service
public class SkillService extends BaseService<Skill> {
    private final SkillRepository skillRepository;
    private final VacancyRepository vacancyRepository;
    public SkillService(SkillRepository repository, VacancyRepository vacancyRepository) {
        super(repository);
        this.skillRepository = repository;
        this.vacancyRepository = vacancyRepository;
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
    public List<Skill> findAll(List<UUID> skillIds) {
        return skillRepository.findAllById(skillIds);
    }

    @Override
    public Skill update(Skill skill) {
        Skill existing = skillRepository.findById(skill.getId())
                .orElseThrow(() -> new EntityNotFoundException("Skill not found"));

        existing.setName(skill.getName()); // обновляем только имя
        skillRepository.save(existing);
        return existing;
    }
    public Page<Skill> findAllExcludingIds(List<UUID> excludedIds, Pageable pageable) {
        if (excludedIds == null || excludedIds.isEmpty()) {

            return skillRepository.findAll(pageable);
        }
        return skillRepository.findAllExcludingIds(excludedIds, pageable);
    }

}
