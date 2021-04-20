package com.hescha.sportstore.service;

import com.hescha.sportstore.entity.Order;
import com.hescha.sportstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends CrudImpl<Order> {

    public OrderRepository repository;

    @Autowired
    public OrderService(OrderRepository repository) {
        super(repository);
        this.repository = repository;
    }

}
