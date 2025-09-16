package ru.zyuzyukov.kurs_3_db.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zyuzyukov.kurs_3_db.entity.Employer;
import ru.zyuzyukov.kurs_3_db.entity.Entitytable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployerDto implements CRUDable {
    //при создании не указываем
    private UUID id;
    @NotBlank()
    @Column(name = "name", length = 50)
    private String name;
    //при создании не указываем
    private List<UUID> vacancyList = new ArrayList<>();
    @NotNull
    private Boolean active = true;



}
