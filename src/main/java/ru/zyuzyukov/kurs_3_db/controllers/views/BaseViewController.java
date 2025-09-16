package ru.zyuzyukov.kurs_3_db.controllers.views;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zyuzyukov.kurs_3_db.dto.CRUDable;
import ru.zyuzyukov.kurs_3_db.entity.Entitytable;
import ru.zyuzyukov.kurs_3_db.mapper.Mapper;
import ru.zyuzyukov.kurs_3_db.service.BaseService;

import java.util.UUID;
import java.util.function.Supplier;

public abstract class BaseViewController<T extends CRUDable, D extends Entitytable> {

    private final BaseService<D> baseService;
    private final Mapper<T, D> mapper;
    private final Supplier<T> dtoSupplier; // Для создания нового DTO
    private final String smallName;        // Например "employer"
    private final String generalName;         // Например "employers"

    public BaseViewController(BaseService<D> baseService,
                              Mapper<T, D> mapper,
                              Supplier<T> dtoSupplier,
                              String smallName,
                              String generalName) {
        this.baseService = baseService;
        this.mapper = mapper;
        this.dtoSupplier = dtoSupplier;
        this.smallName = smallName;
        this.generalName = generalName;
    }


    @GetMapping
    public String list(@PageableDefault(size = 10, sort = "id") Pageable pageable,
                       Model model) {
        Page<T> page = baseService.findAll(pageable).map(mapper::toDto);
        model.addAttribute(generalName, page.getContent());
        model.addAttribute("page", page);
        return smallName + "/list";
    }


    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute( dtoSupplier.get());
        return smallName + "/create";
    }


    @PostMapping("/create")
    public  String create(@Valid @ModelAttribute T dto,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return smallName + "/create";
        }
        D entity = mapper.toCreateEntity(dto);
        baseService.save(entity);
        return "redirect:/view/" + smallName;
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable UUID id, Model model) {
        D entity = baseService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(smallName + " not found"));
        model.addAttribute( mapper.toDto(entity));
        return smallName + "/edit";
    }


    @PostMapping("/edit")
    public String update(@Valid @ModelAttribute T dto,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return smallName + "/edit";
        }
        baseService.update(mapper.toCreateEntity(dto));
        return "redirect:/view/" + smallName;
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable UUID id) {
        baseService.delete(id);
        return "redirect:/view/" + smallName;
    }
}
