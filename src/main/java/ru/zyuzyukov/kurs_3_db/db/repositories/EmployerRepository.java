package ru.zyuzyukov.kurs_3_db.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zyuzyukov.kurs_3_db.db.entity.Employer;

import java.util.UUID;

public interface EmployerRepository extends JpaRepository<Employer, UUID>{

}
