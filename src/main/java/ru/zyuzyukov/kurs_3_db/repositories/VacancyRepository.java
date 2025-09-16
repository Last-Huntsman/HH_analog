package ru.zyuzyukov.kurs_3_db.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.zyuzyukov.kurs_3_db.entity.Vacancy;

import java.util.List;
import java.util.UUID;

public interface VacancyRepository extends JpaRepository<Vacancy, UUID> {
   Page<Vacancy> findByEmployerId(UUID employerId, Pageable pageable);

}
