package ru.zyuzyukov.kurs_3_db.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class WorkerDto implements CRUDable {

    private UUID id;
    @NotBlank()
    @Column(name = "name", length = 50)
    private String name;
    @Min(0)
    @Max(100)
    private Integer experience=0;

    private List<UUID> workerSkillsId = new ArrayList<>();
}
