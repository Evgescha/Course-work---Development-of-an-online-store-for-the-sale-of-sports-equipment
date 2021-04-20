package com.hescha.sportstore.controller;

import java.security.Principal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.hescha.sportstore.entity.Order;
import com.hescha.sportstore.entity.Status;
import com.hescha.sportstore.entity.User;
import com.hescha.sportstore.service.InventoryService;
import com.hescha.sportstore.service.OrderService;
import com.hescha.sportstore.service.StatusService;
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
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService service;

    @Autowired
    InventoryService serviceInventory;

    @Autowired
    UserServiceImpl serviceUser;

    @Autowired
    StatusService serviceStatus;

    @GetMapping
    String get(Model model) {
        List<Order> list = service.repository.findAll();
        model.addAttribute("list", list);
        return "order-list";
    }


    @RequestMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return "redirect:/order";
    }

    @RequestMapping(path = "/cancelOrder/{id}")
    public String cancel(Model model, @PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return "redirect:/user/history";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(Principal principal) throws Exception {
        User creator = serviceUser.findByUsername(principal.getName());
        Date dates = new Date(System.currentTimeMillis());
        Time times = new Time(System.currentTimeMillis());
        Status inProgress = serviceStatus.read(0);
        Order order = new Order();
        order.setCreator(creator);
        order.setDates(dates);
        order.setTimes(times);
        order.setStatus(inProgress);
        service.create(order);
        return "redirect:/order";
    }

    @RequestMapping(path = "/bookNow", method = RequestMethod.POST)
    public String bookNowPOST(Order order,
                              @Param("inventoryId") Long inventoryId,
                              @Param("timeToo") String timeToo,
                              Model model, Principal principal) {

        return "redirect:/user/history";
    }

}
