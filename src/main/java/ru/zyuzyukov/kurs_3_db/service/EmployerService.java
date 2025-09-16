package ru.zyuzyukov.kurs_3_db.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.zyuzyukov.kurs_3_db.entity.Employer;
import ru.zyuzyukov.kurs_3_db.repositories.EmployerRepository;

import java.util.UUID;
@Service
public class EmployerService extends BaseService<Employer> {
    private final EmployerRepository repository;
    public EmployerService(EmployerRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
