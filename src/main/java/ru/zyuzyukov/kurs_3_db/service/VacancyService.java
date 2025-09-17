package ru.zyuzyukov.kurs_3_db.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.zyuzyukov.kurs_3_db.entity.Vacancy;
import ru.zyuzyukov.kurs_3_db.repositories.EmployerRepository;
import ru.zyuzyukov.kurs_3_db.repositories.VacancyRepository;

import java.util.List;
import java.util.UUID;
@Service
public class VacancyService extends BaseService<Vacancy> {
    private final VacancyRepository repository;
    private final EmployerRepository employerRepository;
    public VacancyService(VacancyRepository repository, EmployerRepository employerRepository) {
        super(repository);
        this.repository = repository;

        this.employerRepository = employerRepository;
    }
    public Page<Vacancy> findAllByEmployerId(UUID employer_id, Pageable pageable) {
        return repository.findByEmployerId(employer_id, pageable);
    }

    public List<Vacancy> findAllById(List<UUID> vacancyList) {
        return repository.findAllById(vacancyList);
    }

    @Override
    public Vacancy update(Vacancy vacancy) {
        Boolean activeEmployer = employerRepository.findById(vacancy.getEmployer().getId()).orElseThrow(()->
                new IllegalArgumentException("emloyer not found")).getActive();
        if(!activeEmployer) {
            vacancy.setActive(false);
        }
        return super.update(vacancy);
    }
}
