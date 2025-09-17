package ru.zyuzyukov.kurs_3_db.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public  class SkillDto implements CRUDable {
    private UUID id;
    private String name;

}
