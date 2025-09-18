package ru.zyuzyukov.kurs_3_db.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
public class Worker implements Entitytable{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @NotBlank()
    @Column(name = "name", length = 50)
    private String name;
    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employment> employments = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "workers_skills",
            joinColumns =@JoinColumn(name= "worker_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> workerSkills = new ArrayList<>();
}
