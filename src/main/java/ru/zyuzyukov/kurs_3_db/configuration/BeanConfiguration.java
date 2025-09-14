package ru.zyuzyukov.kurs_3_db.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.zyuzyukov.kurs_3_db.entity.*;
import ru.zyuzyukov.kurs_3_db.repositories.*;
import ru.zyuzyukov.kurs_3_db.service.BaseService;

import java.util.Base64;
@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    public final EmployerRepository employerRepository;
    public final EmploymentRepository employmentRepository;
    public final SkillRepository skillRepository;
    public final VacancyRepository vacancyRepository;
    public final WorkerRepository workerRepository;
    @Bean
    public BaseService<Employer> getEmployerService() {
        return new BaseService<>(employerRepository);
    }
    @Bean
    public BaseService<Employment> getEmploymentService() {
        return new BaseService<>(employmentRepository);
    }
    @Bean
    public BaseService<Skill> getSkillService() {
        return new BaseService<>(skillRepository);
    }
    @Bean
    public BaseService<Vacancy> getVacancyService() {
        return new BaseService<>(vacancyRepository);
    }
    @Bean
    public BaseService<Worker> getWorkerService() {
        return new BaseService<>(workerRepository);
    }

}
