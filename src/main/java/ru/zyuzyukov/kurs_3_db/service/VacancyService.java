package ru.zyuzyukov.kurs_3_db.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.zyuzyukov.kurs_3_db.entity.Skill;
import ru.zyuzyukov.kurs_3_db.entity.Vacancy;
import ru.zyuzyukov.kurs_3_db.repositories.VacancyRepository;

import java.util.List;
import java.util.UUID;
@Service
public class VacancyService extends BaseService<Vacancy> {
    private final VacancyRepository repository;
    public VacancyService(VacancyRepository repository) {
        super(repository);
        this.repository = repository;
    }
    public Page<Vacancy> findAllByEmployerId(UUID employer_id, Pageable pageable) {
        return repository.findByEmployerId(employer_id, pageable);
    }

    public List<Vacancy> findAllById(List<UUID> vacancyList) {
        return repository.findAllById(vacancyList);
    }
}
