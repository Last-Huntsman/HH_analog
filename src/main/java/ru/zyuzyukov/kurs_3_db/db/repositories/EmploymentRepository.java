package ru.zyuzyukov.kurs_3_db.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.zyuzyukov.kurs_3_db.db.entity.Vacancy;
import ru.zyuzyukov.kurs_3_db.db.entity.Employment;

import java.util.List;
import java.util.UUID;

public interface EmploymentRepository extends JpaRepository<Employment, UUID> {
    List<Employment> findByWorkerId(UUID workerId);
    @Query("SELECT e.vacancy " +
            "FROM Employment e " +
            "GROUP BY e.vacancy " +
            "ORDER BY COUNT(e) DESC")
    List<Vacancy> findVacanciesByPopularity(org.springframework.data.domain.Pageable pageable);

}


