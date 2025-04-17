package com.phegondev.AlekEcommerce.repository;

import com.phegondev.AlekEcommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
