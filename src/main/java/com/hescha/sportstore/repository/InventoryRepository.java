package com.hescha.sportstore.repository;

import com.hescha.sportstore.entity.Inventory;
import com.hescha.sportstore.entity.TypeInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
	
	List<Inventory> findByNameIgnoreCase(String name);

	List<Inventory> findByPrice(float price);

	List<Inventory> findByTypeInventory(TypeInventory typeFood);
}
