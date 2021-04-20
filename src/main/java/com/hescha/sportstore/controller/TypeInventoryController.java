package com.hescha.sportstore.controller;

import java.util.List;

import com.hescha.sportstore.entity.TypeInventory;
import com.hescha.sportstore.service.TypeInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/typeInventory")
public class TypeInventoryController {

    @Autowired
    TypeInventoryService service;

    @GetMapping
    String getCategory(Model model) {
        List<TypeInventory> list = service.repository.findAll();
        model.addAttribute("list", list);
        return "typeInventory-list";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable(name = "id", required =
            false) Long id) {
        if (id != null) {
            TypeInventory entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", new TypeInventory());
        }
        return "typeInventory-add-edit";
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return "redirect:/admin/control";
    }

    @RequestMapping(path = "/id/{id}")
    public String getByTag(Model model, @PathVariable("id") Long id) throws Exception {
        TypeInventory typeInventory = service.read(id);
        model.addAttribute("list", typeInventory.getInventories());
        model.addAttribute("types", service.repository.findAll());
        return "inventory";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(TypeInventory entity) throws Exception {
        service.create(entity);
        return "redirect:/admin/control";
    }
}
