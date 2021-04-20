package com.hescha.sportstore.controller;

import com.hescha.sportstore.service.InventoryService;
import com.hescha.sportstore.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping(path = {"/", "/index"})
public class IndexController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    InventoryService inventoryService;

    @GetMapping
    String getIndex(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("entity",
                    userService.findByUsername(principal.getName()));
        }
        model.addAttribute("inventories",
                inventoryService.repository.findAll());
        return "index";
    }

}
