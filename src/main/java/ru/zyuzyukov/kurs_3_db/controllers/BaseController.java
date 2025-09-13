package ru.zyuzyukov.kurs_3_db;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.zyuzyukov.kurs_3_db.service.BaseService;


import java.util.UUID;

public class BaseController<T> {
    private final BaseService<T> baseService;
    public BaseController(BaseService<T> baseService) {
        this.baseService = baseService;
    }
    public ResponseEntity getAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(baseService.findAll(pageable));
    }
    public ResponseEntity<T> create(T entity) {
        return ResponseEntity.ok(baseService.save(entity));
    }
    public ResponseEntity<T> update(T entity) {
        return ResponseEntity.ok(baseService.update(entity));
    }
    public ResponseEntity delete(UUID id) {
        return ResponseEntity.ok(baseService.delete(id)? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
