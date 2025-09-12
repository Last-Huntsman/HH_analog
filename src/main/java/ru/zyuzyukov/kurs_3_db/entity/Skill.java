package ru.zyuzyukov.kurs_3_db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @NotBlank()

    @Column(name = "name", length = 50)
    private String name;

    @ManyToMany(mappedBy = "vacancySkills")
    private List<Vacancy> vacancies = new ArrayList<>();

    @ManyToMany(mappedBy = "workerSkills")
    private List<Worker> workers = new ArrayList<>();
}
