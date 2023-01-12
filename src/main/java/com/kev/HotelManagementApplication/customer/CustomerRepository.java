package com.kev.HotelManagementApplication.customer;

import com.kev.HotelManagementApplication.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByName(String name);

    Customer findByToken(String token);
}
