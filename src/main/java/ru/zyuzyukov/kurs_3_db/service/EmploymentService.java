package ru.zyuzyukov.kurs_3_db.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.zyuzyukov.kurs_3_db.entity.Employment;
import ru.zyuzyukov.kurs_3_db.repositories.EmploymentRepository;

import java.util.UUID;
@Service
public class EmploymentService extends BaseService<Employment> {
   private final EmploymentRepository repository;
    public EmploymentService(EmploymentRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
