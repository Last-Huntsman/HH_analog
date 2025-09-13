package ru.zyuzyukov.kurs_3_db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zyuzyukov.kurs_3_db.service.CRUDable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Worker implements CRUDable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @NotBlank()
    @Column(name = "name", length = 50)
    private String name;
    @Min(0)
    @Max(100)
    private Integer experience=0;


    @ManyToMany
    @JoinTable(name = "workers_skills",
            joinColumns =@JoinColumn(name= "worker_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> workerSkills = new ArrayList<>();
}
