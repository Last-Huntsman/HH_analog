package ru.zyuzyukov.kurs_3_db.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.zyuzyukov.kurs_3_db.db.entity.Worker;

import java.util.UUID;

public interface WorkerRepository extends JpaRepository<Worker, UUID> {

}
