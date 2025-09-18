package ru.zyuzyukov.kurs_3_db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zyuzyukov.kurs_3_db.entity.Employment;

import java.util.List;
import java.util.UUID;

public interface EmploymentRepository extends JpaRepository<Employment, UUID> {
    List<Employment> findByWorkerId(UUID workerId);
}
