package com.kev.HotelManagementApplication.factory;

import com.kev.HotelManagementApplication.customer.CustomerDTO;
import com.kev.HotelManagementApplication.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class DTOFactory {

    public CustomerDTO createDTO(Customer customer) {
        CustomerDTO customerDTO =
                new CustomerDTO(
                        customer.getId(),
                        customer.getName());
        return customerDTO;
    }

}
