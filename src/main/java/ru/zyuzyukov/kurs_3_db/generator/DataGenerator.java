package ru.zyuzyukov.kurs_3_db.generator;

import org.springframework.stereotype.Component;
import ru.zyuzyukov.kurs_3_db.entity.*;
import ru.zyuzyukov.kurs_3_db.repositories.*;

import java.time.LocalDate;
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

        // --- Компании ---
        List<Employer> employers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Employer employer = new Employer();
            employer.setName("Company " + i);
            employer.setActive(true);
            employers.add(employer);
        }
        employerRepository.saveAll(employers);

        // --- Навыки ---
        List<Skill> skills = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            Skill skill = new Skill();
            skill.setName("Skill " + i);
            skills.add(skill);
        }
        skillRepository.saveAll(skills);

        // --- Вакансии ---
        List<Vacancy> vacancies = new ArrayList<>();
        for (Employer employer : employers) {
            int vacancyCount = 2 + random.nextInt(3); // 2–4 вакансии
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

        // --- Работники ---
        List<Worker> workers = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            Worker worker = new Worker();
            worker.setName("Worker " + i);

            Collections.shuffle(skills);
            worker.setWorkerSkills(new ArrayList<>(skills.subList(0, 1 + random.nextInt(4))));
            workers.add(worker);
        }
        workerRepository.saveAll(workers);

        // --- Трудоустройства ---
        List<Employment> employments = new ArrayList<>();
        for (Worker worker : workers) {
            int employmentCount = 1 + random.nextInt(5); // 1–5 записей о стаже
            Set<Vacancy> usedVacancies = new HashSet<>();

            for (int k = 0; k < employmentCount; k++) {
                Vacancy vacancy;
                do {
                    vacancy = vacancies.get(random.nextInt(vacancies.size()));
                } while (!usedVacancies.add(vacancy)); // чтобы не было дубликатов

                LocalDate start = LocalDate.now()
                        .minusYears(random.nextInt(10))
                        .minusMonths(random.nextInt(12));

                LocalDate end = start.plusMonths(6 + random.nextInt(36)); // от 0.5 до 3 лет
                if (end.isAfter(LocalDate.now())) {
                    end = null; // текущее место работы
                }

                Employment employment = new Employment();
                employment.setWorker(worker);
                employment.setVacancy(vacancy);
                employment.setDate_open(start);
                employment.setDate_closed(end);

                employments.add(employment);
            }
        }
        employmentRepository.saveAll(employments);

        // --- Пересчёт опыта ---

        workerRepository.saveAll(workers);

        System.out.println("✅ Генерация данных завершена!");
    }
}
