package com.kev.HotelManagementApplication.customer;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<CustomerDTO> getCustomerList() {
        return customerRepository.findAll();
    }
}
