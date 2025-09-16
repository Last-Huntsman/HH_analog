package ru.zyuzyukov.kurs_3_db.controllers.rest;

import jakarta.validation.Valid;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zyuzyukov.kurs_3_db.entity.Entitytable;
import ru.zyuzyukov.kurs_3_db.mapper.Mapper;
import ru.zyuzyukov.kurs_3_db.service.BaseService;
import ru.zyuzyukov.kurs_3_db.dto.CRUDable;


import java.util.Optional;
import java.util.UUID;

public class BaseController<T extends CRUDable,D extends Entitytable> {

    private final BaseService<D> baseService;
    private final Mapper<T,D> mapper;

    public BaseController(BaseService<D> baseService,Mapper mapper) {
        this.baseService = baseService;
        this.mapper = mapper;
    }
    @GetMapping
    protected Page<T> getAll( @ParameterObject @PageableDefault(size = 20, sort = "id") Pageable pageable)  {
        Page<D> page = baseService.findAll(pageable);
        return page.map(mapper::toDto);
    }
    @GetMapping("/{id}")
    protected ResponseEntity<T> getById(@PathVariable UUID id) {
        D entity = baseService.findById(id).orElse(null);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        T dto = mapper.toDto(entity);
        return ResponseEntity.of(Optional.ofNullable(dto));
    }
    @PostMapping
    protected ResponseEntity<T> create(@Valid @RequestBody T dto) {
        D entity =  baseService.save(mapper.toCreateEntity(dto))  ;
        T dtoResult = mapper.toDto(entity);
        return ResponseEntity.ok(dtoResult);
    }
    @PutMapping
    protected ResponseEntity<T> update( @Valid @RequestBody T dto) {
        D entity =  baseService.update(mapper.toCreateEntity(dto))  ;
        T dtoResult = mapper.toDto(entity);
        return ResponseEntity.ok(dtoResult);
    }
    @DeleteMapping("/{id}")
    protected ResponseEntity<Void> delete(@PathVariable  UUID id) {
        return baseService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
