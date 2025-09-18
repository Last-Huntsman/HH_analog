package ru.zyuzyukov.kurs_3_db.db.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.zyuzyukov.kurs_3_db.db.entity.Vacancy;

import java.util.UUID;

public interface VacancyRepository extends JpaRepository<Vacancy, UUID> , JpaSpecificationExecutor<Vacancy> {
   Page<Vacancy> findByEmployerId(UUID employerId, Pageable pageable);

}
