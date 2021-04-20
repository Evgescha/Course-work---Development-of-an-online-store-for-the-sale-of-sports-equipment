package com.hescha.sportstore.controller;

import com.hescha.sportstore.entity.Order;
import com.hescha.sportstore.entity.OrderList;
import com.hescha.sportstore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    TypeInventoryService serviceTypeInventory;

    @Autowired
    InventoryService serviceInventory;

    @Autowired
    UserServiceImpl serviceUser;

    @Autowired
    OrderService serviceOrder;

    @Autowired
    OrderListService serviceOrderList;

    @Autowired
    StatusService serviceStatus;

    @GetMapping
    @RequestMapping("/control")
    String getIndex(Model model) {
        model.addAttribute("typesInventory",
                serviceTypeInventory.repository.findAll());
        model.addAttribute("inventories",
                serviceInventory.repository.findAll());
        model.addAttribute("users", serviceUser.repository.findAll());
        return "control";
    }

    @GetMapping
    @RequestMapping("/booking")
    String getBooking(Model model) {
        List<Order> all = serviceOrder.repository.findAll();
        Collections.reverse(all);
        model.addAttribute("list", all);
        return "booking";
    }


    @GetMapping
    @RequestMapping("/deleteOrderUser/{id}")
    String deleteOrderUser(Model model, @PathVariable("id") Long id) throws Exception {
        deleteOrder(id);
        return "redirect:/user/history";
    }

    @GetMapping
    @RequestMapping("/deleteOrderAdmin/{id}")
    String deleteOrderAdmin(Model model, @PathVariable("id") Long id) throws Exception {
        deleteOrder(id);
        return "redirect:/admin/booking";
    }

    private void deleteOrder(@PathVariable("id") Long id) throws Exception {
        Order order = serviceOrder.read(id);
        for (OrderList orderList : order.getOrderLists()) {
            serviceOrderList.delete(orderList.getId());
        }
        serviceOrder.delete(id);
    }

    @GetMapping
    @RequestMapping("/aproveOrderUser/{id}")
    String aproveOrderUser(Model model, @PathVariable("id") Long id) throws Exception {
        Order order = serviceOrder.read(id);
        order.setStatus(serviceStatus.read(2));
        serviceOrder.update(order);
        return "redirect:/user/history";
    }

    @GetMapping
    @RequestMapping("/aproveOrderAdmin/{id}")
    String aproveOrderAdmin(Model model, @PathVariable("id") Long id) throws Exception {
        Order order = serviceOrder.read(id);
        order.setStatus(serviceStatus.read(3));
        serviceOrder.update(order);
        return "redirect:/admin/booking";
    }
}
