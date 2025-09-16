package ru.zyuzyukov.kurs_3_db.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zyuzyukov.kurs_3_db.entity.Employer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacancyDto implements CRUDable {
    //при создании не указываем
    private UUID id;

    private UUID employerId;
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

    private List<UUID> vacancySkills = new ArrayList<>();

}
