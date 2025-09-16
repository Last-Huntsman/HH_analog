package ru.zyuzyukov.kurs_3_db.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.zyuzyukov.kurs_3_db.entity.Worker;

import java.util.List;
import java.util.UUID;
@Service
public class WorkerService extends BaseService<Worker> {
    private final JpaRepository<Worker, UUID> repository;
    public WorkerService(JpaRepository<Worker, UUID> repository) {
        super(repository);
        this.repository = repository;
    }
    public List<Worker> findAllById(List<UUID> vacancySkills) {
        return repository.findAllById(vacancySkills);
    }
}
