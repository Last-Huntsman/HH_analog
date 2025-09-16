package ru.zyuzyukov.kurs_3_db.controllers.views;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zyuzyukov.kurs_3_db.dto.VacancyDto;
import ru.zyuzyukov.kurs_3_db.entity.Vacancy;
import ru.zyuzyukov.kurs_3_db.mapper.VacancyMapper;
import ru.zyuzyukov.kurs_3_db.service.VacancyService;

import java.util.UUID;

@Controller("vacancyViewController")
@RequestMapping("/view/vacancy")
public class VacancyController extends BaseViewController<VacancyDto,Vacancy> {
    private final VacancyService service;
    private final VacancyMapper mapper;
    protected VacancyController(VacancyService service, VacancyMapper mapper) {
        super(service,
                mapper,
                VacancyDto::new,
                "vacancy",
                "vacancies");
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/createvacancy/{id}")
    public String showCreateVacancyForm(@PathVariable("id") UUID employerId, Model model) {
        VacancyDto dto = new VacancyDto();
        dto.setEmployerId(employerId); // фиксируем employerId
        model.addAttribute("vacancyDto", dto);
        return "vacancy/createvacancy";
    }

    @PostMapping("/createvacancy")
    public String createVacancy(@Valid @ModelAttribute VacancyDto vacancyDto,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "vacancy/createvacancy";
        }

        if (vacancyDto.getEmployerId() == null) {
            throw new IllegalArgumentException("Employer ID must not be null");
        }

        Vacancy vacancy = mapper.toCreateEntity(vacancyDto);
        service.save(vacancy);

        return "redirect:/view/employer/" + vacancyDto.getEmployerId() + "/vacancies";
    }


    @Override
    public String update(@Valid @ModelAttribute VacancyDto vacancyDto,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "vacancy/edit";
        }

        Vacancy vacancy = mapper.toCreateEntity(vacancyDto);
        // Фиксируем работодателя через ID из DTO
//        vacancy.setEmployer(
//                service.findEmployerById(vacancyDto.getEmployerId())
//                        .orElseThrow(() -> new IllegalArgumentException("Employer not found"))
//        );

        service.save(vacancy);
        return "redirect:/view/employer/" + vacancy.getEmployer().getId() + "/vacancies";
    }


}
