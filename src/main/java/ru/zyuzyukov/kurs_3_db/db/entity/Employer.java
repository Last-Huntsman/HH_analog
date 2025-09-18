package ru.zyuzyukov.kurs_3_db.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Employer implements Entitytable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @NotBlank()
    @Column(name = "name", length = 50, unique = true)
    private String name;

    @NotNull
    private Boolean active = true;

    @OneToMany(mappedBy = "employer",orphanRemoval = true)
    private List<Vacancy> vacancyList = new ArrayList<>();


}
