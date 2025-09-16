package ru.zyuzyukov.kurs_3_db.generator;

import org.springframework.stereotype.Component;
import ru.zyuzyukov.kurs_3_db.entity.*;
import ru.zyuzyukov.kurs_3_db.repositories.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Component
public class DataGenerator {

    private final EmployerRepository employerRepository;
    private final VacancyRepository vacancyRepository;
    private final SkillRepository skillRepository;
    private final WorkerRepository workerRepository;
    private final EmploymentRepository employmentRepository;

    private final Random random = new Random();

    public DataGenerator(EmployerRepository employerRepository, VacancyRepository vacancyRepository,
                         SkillRepository skillRepository, WorkerRepository workerRepository,
                         EmploymentRepository employmentRepository) {
        this.employerRepository = employerRepository;
        this.vacancyRepository = vacancyRepository;
        this.skillRepository = skillRepository;
        this.workerRepository = workerRepository;
        this.employmentRepository = employmentRepository;

//        generateData();
    }

    private void generateData() {

        List<Employer> employers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Employer employer = new Employer();
            employer.setName("Company " + i);
            employers.add(employer);
            employer.setActive(true);
        }
        employerRepository.saveAll(employers);

        List<Skill> skills = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Skill skill = new Skill();
            skill.setName("Skill " + i);
            skills.add(skill);
        }

        skillRepository.saveAll(skills);
        List<Vacancy> vacancies = new ArrayList<>();
        for (Employer employer : employers) {
            int vacancyCount = 2 + random.nextInt(2); // 2 или 3
            for (int j = 1; j <= vacancyCount; j++) {
                Vacancy vacancy = new Vacancy();
                vacancy.setEmployer(employer);
                vacancy.setPost("Position " + j + " @ " + employer.getName());
                vacancy.setDescription("Job description for " + vacancy.getPost());
                vacancy.setSalary(40000 + random.nextInt(60000));
                vacancy.setActive(true);
                Collections.shuffle(skills);
                vacancy.setVacancySkills(new ArrayList<>(skills.subList(0, 2 + random.nextInt(4))));
                vacancies.add(vacancy);
            }
        }
        vacancyRepository.saveAll(vacancies);




        List<Worker> workers = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            Worker worker = new Worker();
            worker.setName("Worker " + i);

            Collections.shuffle(skills);
            worker.setWorkerSkills(new ArrayList<>(skills.subList(0, 2 + random.nextInt(4))));
            workers.add(worker);
        }

        workerRepository.saveAll(workers);


        List<Employment> employments = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Worker worker = workers.get(random.nextInt(workers.size()));
            Vacancy vacancy = vacancies.get(random.nextInt(vacancies.size()));

            LocalDateTime start = LocalDateTime.now().minusYears(random.nextInt(10))
                    .minusMonths(random.nextInt(12));
            LocalDateTime end = start.plusMonths(6 + random.nextInt(48)); // от полугода до 4 лет

            Employment employment = new Employment();
            employment.setWorker(worker);
            employment.setVacancy(vacancy);
            employment.setDate_open(start);
            employment.setDate_closed(end);

            employments.add(employment);
        }
        employmentRepository.saveAll(employments);


        for (Worker worker : workers) {
            List<Employment> workerEmployments = employments.stream()
                    .filter(e -> e.getWorker().equals(worker))
                    .toList();

            int totalExperience = 0;
            for (Employment e : workerEmployments) {
                long years = ChronoUnit.YEARS.between(e.getDate_open(), e.getDate_closed());
                totalExperience += (int) years;
            }
            worker.setExperience(totalExperience);
        }
        workerRepository.saveAll(workers);

        System.out.println(" Генерация данных завершена!");
    }
}
