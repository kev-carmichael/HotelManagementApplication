package com.kev.HotelManagementApplication.customer;


import com.kev.HotelManagementApplication.entity.Customer;
import com.kev.HotelManagementApplication.factory.DTOFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private DTOFactory dtoFactory;

    public List<CustomerDTO> getCustomerList() {
        List<CustomerDTO> list = new ArrayList<>();
        for (Customer customer : customerRepository.findAll()){
            CustomerDTO customerDTO = dtoFactory.createDTO(customer);
            list.add(customerDTO);
        }
        return list;
    }
    
}
