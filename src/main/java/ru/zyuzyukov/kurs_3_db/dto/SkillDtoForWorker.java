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
public class SkillDtoForWorker extends SkillDto {
    public SkillDtoForWorker(UUID id, String name, List<Worker> workers) {
        super(id, name);
        this.workers = workers;
    }

    private List<Worker> workers = new ArrayList<>();
}
