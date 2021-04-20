package com.hescha.sportstore.controller;

import java.security.Principal;
import java.util.List;

import com.hescha.sportstore.entity.Inventory;
import com.hescha.sportstore.entity.TypeInventory;
import com.hescha.sportstore.entity.User;
import com.hescha.sportstore.service.InventoryService;
import com.hescha.sportstore.service.TypeInventoryService;
import com.hescha.sportstore.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    InventoryService service;

    @Autowired
    TypeInventoryService typeInventoryService;

    @Autowired
    UserServiceImpl userService;

    @GetMapping
    String getService(Model model) {
        List<Inventory> list = service.repository.findAll();
        model.addAttribute("list", list);
        model.addAttribute("types", typeInventoryService.repository.findAll());
        return "inventory";
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return "redirect:/admin/control";
    }

    @RequestMapping(path = "/id/{id}")
    public String description(Model model, @PathVariable("id") Long id,
                              Principal principal) throws Exception {
        Inventory inventory = service.read(id);
        model.addAttribute("entity", inventory);
        return "inventoryDescription";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable(name = "id", required =
            false) Long id, Principal principal) {

        List<TypeInventory> types = typeInventoryService.repository.findAll();

        if (id != null) {
            Inventory entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", new Inventory());
        }
        model.addAttribute("types", types);
        return "inventory-add-edit";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(Inventory entity, @Param("catId") Long catId) throws Exception {

        entity.setTypeInventory(typeInventoryService.read(catId));
        service.create(entity);
        return "redirect:/admin/control";
    }
}
