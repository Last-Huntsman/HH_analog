package ru.zyuzyukov.kurs_3_db.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zyuzyukov.kurs_3_db.service.CRUDable;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmploymentDto  implements CRUDable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID worker_id;

    private UUID vacancy_id;
    private LocalDateTime end_date;
}
