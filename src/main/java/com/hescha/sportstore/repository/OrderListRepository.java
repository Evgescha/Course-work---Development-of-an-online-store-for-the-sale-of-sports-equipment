package com.hescha.sportstore.repository;

import com.hescha.sportstore.entity.Inventory;
import com.hescha.sportstore.entity.Order;
import com.hescha.sportstore.entity.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderListRepository extends JpaRepository<OrderList, Long> {
	List<OrderList> findByOrder(Order order);

	List<Order> findByOrderAndInventory(Order order, Inventory inventory);
}
