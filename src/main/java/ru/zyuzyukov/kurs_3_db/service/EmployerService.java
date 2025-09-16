package ru.zyuzyukov.kurs_3_db.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.zyuzyukov.kurs_3_db.entity.Employer;
import ru.zyuzyukov.kurs_3_db.entity.Vacancy;
import ru.zyuzyukov.kurs_3_db.repositories.EmployerRepository;

import java.util.List;
import java.util.UUID;
@Service
public class EmployerService extends BaseService<Employer> {
    private final EmployerRepository repository;
    public EmployerService(EmployerRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Employer update(Employer employer) {
        List<Vacancy> list =  repository.findById(employer.getId()).orElse(employer).getVacancyList();
        employer.setVacancyList(list);
        return super.update(employer);
    }

}
