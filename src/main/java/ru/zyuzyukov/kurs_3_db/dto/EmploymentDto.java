package ru.zyuzyukov.kurs_3_db.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmploymentDto  implements CRUDable {

    //при создании не указываем
    private UUID id;
    //не обязательный
    private UUID worker_id;

    private UUID vacancy_id;
    //при создании не указываем
    private LocalDate date_open;
    // не обязательный параметр
    private LocalDate date_close;
}
