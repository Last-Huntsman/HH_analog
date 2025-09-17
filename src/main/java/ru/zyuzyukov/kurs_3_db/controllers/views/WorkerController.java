package ru.zyuzyukov.kurs_3_db.controllers.views;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zyuzyukov.kurs_3_db.controllers.rest.BaseController;
import ru.zyuzyukov.kurs_3_db.dto.EmployerDto;
import ru.zyuzyukov.kurs_3_db.dto.EmploymentDto;
import ru.zyuzyukov.kurs_3_db.dto.WorkerDto;
import ru.zyuzyukov.kurs_3_db.entity.Worker;
import ru.zyuzyukov.kurs_3_db.mapper.WorkerMapper;
import ru.zyuzyukov.kurs_3_db.service.BaseService;
import ru.zyuzyukov.kurs_3_db.service.WorkerService;

import java.util.UUID;

@Controller("workerViewController")
@RequestMapping("/view/worker")
public class WorkerController  {
    private final WorkerService workerService;
    private final WorkerMapper workerMapper;

    public WorkerController(WorkerService workerService, WorkerMapper workerMapper) {
        this.workerService = workerService;
        this.workerMapper = workerMapper;
    }

    /** список работодателей */
    @GetMapping
    public String list(@PageableDefault(size = 10, sort = "id") Pageable pageable,
                       Model model) {
       Page<WorkerDto> page = workerService.findAll(pageable).map(workerMapper::toDto);
       model.addAttribute("page", page);
       model.addAttribute("workers",page.getContent());
       return "worker/list";
    }

    /** форма создания */
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        WorkerDto worker = new WorkerDto();
        model.addAttribute("workerDto",worker);
        return "worker/create";
    }

    /** обработка создания */
    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("workerDto") WorkerDto dto,
                         BindingResult bindingResult) {

        Worker worker = workerMapper.toCreateEntity(dto);
        workerService.save(worker);
        return "redirect:/view/worker";
    }

    /** форма редактирования */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable UUID id, Model model) {
        Worker worker = workerService.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("worker not found"));
        WorkerDto workerDto = workerMapper.toDto(worker);
        model.addAttribute("workerDto",workerDto);
        return "worker/edit";
    }

    /** обработка редактирования */
    @PostMapping("/edit")
    public String update(@Valid @ModelAttribute("workerDto") WorkerDto dto,
                         BindingResult bindingResult) {
        Worker worker = workerMapper.toCreateEntity(dto);
        workerService.update(worker);
        return "redirect:/view/worker";
    }
   @GetMapping("/delete/{id}")
    public String delete(@PathVariable UUID id) {
        workerService.delete(id);
        return "redirect:/view/worker";
    }

}
