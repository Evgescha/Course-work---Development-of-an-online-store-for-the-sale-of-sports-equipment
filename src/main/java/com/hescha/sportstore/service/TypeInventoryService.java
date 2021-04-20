package com.hescha.sportstore.service;

import com.hescha.sportstore.entity.TypeInventory;
import com.hescha.sportstore.repository.TypeInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeInventoryService extends CrudImpl<TypeInventory> {

    public TypeInventoryRepository repository;

    @Autowired
    public TypeInventoryService(TypeInventoryRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public TypeInventory findByName(String name) {
        return repository.findByNameIgnoreCase(name);
    }

}
