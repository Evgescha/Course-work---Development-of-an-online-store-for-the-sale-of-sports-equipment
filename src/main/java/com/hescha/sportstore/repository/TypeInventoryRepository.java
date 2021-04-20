package com.hescha.sportstore.repository;

import com.hescha.sportstore.entity.TypeInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeInventoryRepository extends JpaRepository<TypeInventory,
        Long> {
    TypeInventory findByNameIgnoreCase(String name);
}
