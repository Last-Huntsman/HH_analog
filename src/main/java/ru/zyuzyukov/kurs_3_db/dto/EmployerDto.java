package ru.zyuzyukov.kurs_3_db.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zyuzyukov.kurs_3_db.service.CRUDable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployerDto implements CRUDable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank()
    @Column(name = "name", length = 50)
    private String name;
    private List<UUID> vacancyList = new ArrayList<>();
}
