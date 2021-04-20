package com.hescha.sportstore.service;

import com.hescha.sportstore.entity.Inventory;
import com.hescha.sportstore.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService extends CrudImpl<Inventory> {

    public InventoryRepository repository;

    @Autowired
    public InventoryService(InventoryRepository repository) {
        super(repository);
        this.repository = repository;
    }


}
