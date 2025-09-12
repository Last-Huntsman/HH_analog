package ru.zyuzyukov.kurs_3_db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zyuzyukov.kurs_3_db.entity.Employer;

import java.util.UUID;

public interface VacancyRepository extends JpaRepository<Employer, UUID>{

}
