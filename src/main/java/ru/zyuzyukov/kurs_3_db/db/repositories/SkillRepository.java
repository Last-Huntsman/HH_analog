package ru.zyuzyukov.kurs_3_db.db.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.zyuzyukov.kurs_3_db.db.entity.Skill;

import java.util.List;
import java.util.UUID;

public interface SkillRepository extends JpaRepository<Skill, UUID> {
    Page<Skill> findByVacancies_Id(UUID vacancyId, Pageable pageable);
    List<Skill> findByVacancies_Id(UUID vacancyId);
    List<Skill> findByWorkers_Id(UUID vacancyId);
}
