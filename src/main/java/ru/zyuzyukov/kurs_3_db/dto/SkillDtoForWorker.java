package ru.zyuzyukov.kurs_3_db.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zyuzyukov.kurs_3_db.entity.Vacancy;
import ru.zyuzyukov.kurs_3_db.entity.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SkillDtoForWorker extends SkillDto {
    //при создании не указываем
    private UUID id;
    @NotBlank()
    @Column(name = "name", length = 50)
    private String name;



    private List<Worker> workers = new ArrayList<>();
}
