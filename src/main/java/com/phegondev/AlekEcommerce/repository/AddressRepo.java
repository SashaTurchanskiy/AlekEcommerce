package com.phegondev.AlekEcommerce.repository;

import com.phegondev.AlekEcommerce.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Long> {
}
