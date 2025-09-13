package ru.zyuzyukov.kurs_3_db.controllers;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zyuzyukov.kurs_3_db.service.BaseService;
import ru.zyuzyukov.kurs_3_db.service.CRUDable;


import java.util.UUID;
//TODO Сделать дтошки чтобы не отдавать пользоавтелю лишние данные
public class BaseController<T extends CRUDable> {

    private final BaseService<T> baseService;

    public BaseController(BaseService<T> baseService) {
        this.baseService = baseService;
    }
    @GetMapping
    protected Page<T> getAll(@PageableDefault(size = 20, sort = "createdAt") Pageable pageable)  {
        return baseService.findAll(pageable);
    }
    @GetMapping("/{id}")
    protected ResponseEntity<T> getById(@PathVariable UUID id) {
        return ResponseEntity.of(baseService.findById(id));
    }
    @PostMapping
    protected ResponseEntity<T> create(@Valid @RequestBody T entity) {
        return ResponseEntity.ok(baseService.save(entity));
    }
    @PutMapping
    protected ResponseEntity<T> update( @Valid @RequestBody T entity) {
        return ResponseEntity.ok(baseService.update(entity));
    }
    @DeleteMapping("/{id}")
    protected ResponseEntity<Void> delete(@PathVariable  UUID id) {
        return baseService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
