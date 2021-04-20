package com.hescha.sportstore.service;

import com.hescha.sportstore.entity.OrderList;
import com.hescha.sportstore.repository.OrderListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderListService extends CrudImpl<OrderList> {

    public OrderListRepository repository;

    @Autowired
    public OrderListService(OrderListRepository repository) {
        super(repository);
        this.repository = repository;
    }

}
