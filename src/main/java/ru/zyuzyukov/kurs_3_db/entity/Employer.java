package ru.zyuzyukov.kurs_3_db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zyuzyukov.kurs_3_db.service.CRUDable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Employer implements CRUDable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @NotBlank()
    @Column(name = "name", length = 50)
    private String name;
    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Vacancy> vacancyList = new ArrayList<>();
}
