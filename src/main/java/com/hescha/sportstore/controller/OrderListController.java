package com.hescha.sportstore.controller;

import com.hescha.sportstore.entity.*;
import com.hescha.sportstore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Controller
@RequestMapping("/orderList")
public class OrderListController {
    private final long STATUS_IN_PROGRESS = 1;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    OrderListService service;

    @Autowired
    OrderService orderService;

    @Autowired
    StatusService statusService;

    @Autowired
    InventoryService inventoryService;

    @GetMapping
    String getService(Model model) {
        List<OrderList> list = service.repository.findAll();
        model.addAttribute("list", list);
        return "inventory";
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(@PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return "redirect:/admin/control";
    }

    @RequestMapping(path = "/cancel/{id}")
    public String cancel(@PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return "redirect:/user/history";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(OrderList entity,
                                 @Param("inventory_id") Long inventory_id,
                                 @Param("count") int count,
                                 Principal principal) throws Exception {
        User user = userService.findByUsername(principal.getName());
        Order order = orderService.repository.findByCreatorAndStatus(user,
                statusService.read(STATUS_IN_PROGRESS));

        if (order == null) {
            createOrderForPrincipal(user);
            order = orderService.repository.findByCreatorAndStatus(user,
                    statusService.read(STATUS_IN_PROGRESS));
        }

        entity.setInventory(inventoryService.read(inventory_id));
        entity.setCount(count);
        entity.setOrder(order);
        service.create(entity);
        return "redirect:/user/history";
    }

    private void createOrderForPrincipal(User user) {
        Date dates = new Date(System.currentTimeMillis());
        Time times = new Time(System.currentTimeMillis());
        Status inProgress = statusService.read(STATUS_IN_PROGRESS);

        Order order = new Order();
        order.setCreator(user);
        order.setDates(dates);
        order.setTimes(times);
        order.setStatus(inProgress);
        orderService.create(order);
    }
}
