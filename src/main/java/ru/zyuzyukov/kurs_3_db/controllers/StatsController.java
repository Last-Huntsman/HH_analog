package ru.zyuzyukov.kurs_3_db.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zyuzyukov.kurs_3_db.service.StatsService;
import ru.zyuzyukov.kurs_3_db.service.VacancyService;
import ru.zyuzyukov.kurs_3_db.service.WorkerService;

@Controller
@RequestMapping("view/stats")
@RequiredArgsConstructor
public class StatsController {
    private final StatsService statsService;

    @GetMapping
    public String stats(Model model) {
        model.addAttribute("stats",statsService.showStats() );
        return "stats";
    }
}
