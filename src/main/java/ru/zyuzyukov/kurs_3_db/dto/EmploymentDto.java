package ru.zyuzyukov.kurs_3_db.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmploymentDto  implements CRUDable {


    private UUID id;

    private UUID worker_id;

    private UUID vacancy_id;
    private LocalDateTime date_open;
    private LocalDateTime date_close;
}
