package com.kev.HotelManagementApplication.customer;

import com.kev.HotelManagementApplication.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
