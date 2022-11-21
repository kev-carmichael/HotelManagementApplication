package com.kev.HotelManagementApplication.customer;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(path = "/all")
    public List<CustomerDTO> getCustomerList() {
        return customerService.getCustomerList();
    }
    
}
