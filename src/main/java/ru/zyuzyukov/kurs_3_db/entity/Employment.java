package ru.zyuzyukov.kurs_3_db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "worker_id", nullable = true)
    private Worker worker;
    @ManyToOne
    @JoinColumn(name = "employment_id", nullable = false)
    private Employment employment;
    private LocalDateTime date_open;
    private LocalDateTime date_closed;
}
