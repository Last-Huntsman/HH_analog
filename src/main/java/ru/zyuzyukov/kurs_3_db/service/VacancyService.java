package ru.zyuzyukov.kurs_3_db.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.zyuzyukov.kurs_3_db.entity.Skill;
import ru.zyuzyukov.kurs_3_db.entity.Vacancy;
import ru.zyuzyukov.kurs_3_db.repositories.EmployerRepository;
import ru.zyuzyukov.kurs_3_db.repositories.SkillRepository;
import ru.zyuzyukov.kurs_3_db.repositories.VacancyRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VacancyService extends BaseService<Vacancy> {
    private final VacancyRepository vacancyRepository;
    private final EmployerRepository employerRepository;
    private final SkillRepository skillRepository;
    public VacancyService(VacancyRepository vacancyRepository, EmployerRepository employerRepository, SkillRepository skillRepository) {
        super(vacancyRepository);
        this.vacancyRepository = vacancyRepository;

        this.employerRepository = employerRepository;
        this.skillRepository = skillRepository;
    }
    public Page<Vacancy> findAllByEmployerId(UUID employer_id, Pageable pageable) {
        return vacancyRepository.findByEmployerId(employer_id, pageable);
    }

    public List<Vacancy> findAllById(List<UUID> vacancyList) {
        return vacancyRepository.findAllById(vacancyList);
    }


    @Override
    public Vacancy update(Vacancy vacancy) {
        Boolean activeEmployer = employerRepository.findById(vacancy.getEmployer().getId()).orElseThrow(()->
                new IllegalArgumentException("emloyer not found")).getActive();
        if(!activeEmployer) {
            vacancy.setActive(false);
        }
        return super.update(vacancy);
    }
    public void removeSkillById(UUID vacancyId, UUID skillId) {
        Vacancy vacancy = vacancyRepository.findById(vacancyId).orElseThrow(
                () -> new IllegalArgumentException("vacancy not found")

        );
        vacancy.setVacancySkills(vacancy.getVacancySkills().stream().filter(skill -> !skill.getId().equals(skillId)).collect(Collectors.toList()));
        vacancyRepository.save(vacancy);
    }
    public void addSkill(UUID vacancyId, UUID skillId) {
        Vacancy vacancy = vacancyRepository.findById(vacancyId).orElseThrow(
                () -> new IllegalArgumentException("vacancy not found")
        );
        Skill skill = skillRepository.findById(skillId).orElseThrow(
                ()-> new IllegalArgumentException("skill not found")
        );
        if (!vacancy.getVacancySkills().contains(skill)) {
            vacancy.getVacancySkills().add(skill);
            vacancyRepository.save(vacancy);
        }
    }
}
