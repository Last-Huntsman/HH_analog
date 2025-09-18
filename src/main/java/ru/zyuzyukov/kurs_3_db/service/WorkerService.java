package ru.zyuzyukov.kurs_3_db.service;

import org.springframework.stereotype.Service;
import ru.zyuzyukov.kurs_3_db.entity.Skill;
import ru.zyuzyukov.kurs_3_db.entity.Vacancy;
import ru.zyuzyukov.kurs_3_db.entity.Worker;
import ru.zyuzyukov.kurs_3_db.repositories.SkillRepository;
import ru.zyuzyukov.kurs_3_db.repositories.WorkerRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WorkerService extends BaseService<Worker> {
    private final WorkerRepository workerRepository;
    private final SkillRepository skillRepository;
    public WorkerService(WorkerRepository workerRepository, SkillRepository skillRepository) {
        super(workerRepository);
        this.workerRepository = workerRepository;
        this.skillRepository = skillRepository;
    }
    public List<Worker> findAllById(List<UUID> vacancySkills) {
        return workerRepository.findAllById(vacancySkills);
    }

    public void removeSkillById(UUID workerId, UUID skillId) {
        Worker worker = workerRepository.findById(workerId).orElseThrow(
                () -> new IllegalArgumentException("worker not found")

        );
        worker.setWorkerSkills(worker.getWorkerSkills().stream()
                .filter(skill -> !skill.getId().equals(skillId))
                .collect(Collectors.toList()));
       workerRepository.save(worker);
    }
    public void addSkill(UUID workerId, UUID skillId) {
        Worker worker = workerRepository.findById(workerId).orElseThrow(
                () -> new IllegalArgumentException("worker not found")

        );
        Skill skill = skillRepository.findById(skillId).orElseThrow(
                ()-> new IllegalArgumentException("skill not found")
        );
        if (!worker.getWorkerSkills().contains(skill)) {
            worker.getWorkerSkills().add(skill);
            workerRepository.save(worker);
        }
    }
}
