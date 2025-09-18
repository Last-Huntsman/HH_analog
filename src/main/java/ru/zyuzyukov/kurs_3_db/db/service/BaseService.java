package ru.zyuzyukov.kurs_3_db.db.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.zyuzyukov.kurs_3_db.db.entity.Entitytable;

import java.util.Optional;
import java.util.UUID;

public abstract class BaseService<T extends Entitytable> {
    private final JpaRepository<T, UUID> repository;

    public BaseService(JpaRepository<T, UUID> repository) {
        this.repository = repository;
    }

    public Optional<T> findById(UUID id) {
        return repository.findById(id);
    }

    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public T save(T t) {
        return repository.save(t);
    }

    public T update(T t) {
        return repository.save(t);
    }

    public boolean delete(UUID id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;

    }
    public long count() {
        return repository.count();
    }


}
