package ru.zyuzyukov.kurs_3_db.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vacancy implements Entitytable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "employer_id", nullable = false)
    private Employer employer;
    @NotNull
    @Min(value = 0)
    private Integer salary;
    @NotBlank
    @Size(max = 5000)
    private String description;
    @NotBlank
    @Size(max = 100)
    private String post;

    @NotNull
    private Boolean active = true;

    @ManyToMany
    @JoinTable(name = "vacancy_skill",
            joinColumns = @JoinColumn(name = "vacancy_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> vacancySkills = new ArrayList<>();

}
