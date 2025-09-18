package ru.zyuzyukov.kurs_3_db.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.zyuzyukov.kurs_3_db.entity.Employment;
import ru.zyuzyukov.kurs_3_db.entity.Vacancy;
import ru.zyuzyukov.kurs_3_db.entity.Worker;
import ru.zyuzyukov.kurs_3_db.repositories.EmploymentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Service

public class EmploymentService extends BaseService<Employment> {
    private final EmploymentRepository employmentRepository;
    private final WorkerService workerService;
    private final VacancyService vacancyService;

    public EmploymentService(EmploymentRepository employmentRepository, WorkerService workerService, VacancyService vacancyService) {
        super(employmentRepository);
        this.employmentRepository = employmentRepository;
        this.workerService = workerService;
        this.vacancyService = vacancyService;

    }
    public List<Employment> findByWorkerId(UUID workerId) {
      return   employmentRepository.findByWorkerId(workerId);
    }
    public List<Employment> getByWorker(UUID workerId) {
        return employmentRepository.findByWorkerId(workerId);
    }

    public Employment addEmployment(UUID workerId, UUID vacancyId, LocalDate start, LocalDate end) {
        Worker worker = workerService.findById(workerId).orElseThrow();
        Vacancy vacancy = vacancyService.findById(vacancyId).orElseThrow();

        Employment employment = new Employment();
        employment.setWorker(worker);
        employment.setVacancy(vacancy);
        employment.setDate_open(start);
        employment.setDate_closed(end);

        return employmentRepository.save(employment);
    }

    public void removeEmployment(UUID id) {
        employmentRepository.deleteById(id);
    }
}
