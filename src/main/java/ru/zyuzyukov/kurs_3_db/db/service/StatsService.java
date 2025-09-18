package ru.zyuzyukov.kurs_3_db.db.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.zyuzyukov.kurs_3_db.db.entity.Vacancy;
import ru.zyuzyukov.kurs_3_db.db.repositories.EmploymentRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatsService {
    private final VacancyService vacancyService;
    private final WorkerService workerService;
    private final EmployerService employerService;
    private final EmploymentRepository employmentRepository;



        public Object showStats() {
            Map<String, Object> stats = new HashMap<>();

            stats.put("totalWorkers", workerService.count());
            stats.put("totalVacancies", vacancyService.count());
            stats.put("totalEmployments", employerService.count());


            List<Vacancy> top5Vacancies =
                    employmentRepository.findVacanciesByPopularity(PageRequest.of(0, 5));
            stats.put("topVacancies", top5Vacancies);

            return stats;

    }

}
