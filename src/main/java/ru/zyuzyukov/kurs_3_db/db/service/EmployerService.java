package ru.zyuzyukov.kurs_3_db.db.service;

import org.springframework.stereotype.Service;
import ru.zyuzyukov.kurs_3_db.db.entity.Employer;
import ru.zyuzyukov.kurs_3_db.db.entity.Vacancy;
import ru.zyuzyukov.kurs_3_db.db.repositories.EmployerRepository;
import ru.zyuzyukov.kurs_3_db.db.repositories.VacancyRepository;

import java.util.List;

@Service
public class EmployerService extends BaseService<Employer> {
    private final EmployerRepository repository;
    private final VacancyRepository vacancyRepository;


    public EmployerService(EmployerRepository repository, VacancyService vacancyService, VacancyRepository vacancyRepository) {
        super(repository);
        this.repository = repository;
        this.vacancyRepository = vacancyRepository;
    }

    @Override
    public Employer update(Employer employer) {
        List<Vacancy> list = repository.findById(employer.getId()).orElse(employer).getVacancyList();
        if(!employer.getActive()) {
            list = list.stream().peek(vacancy->vacancy.setActive(false)).toList();
            vacancyRepository.saveAll(list);
        }
        employer.setVacancyList(list);
        return super.update(employer);
    }

}
