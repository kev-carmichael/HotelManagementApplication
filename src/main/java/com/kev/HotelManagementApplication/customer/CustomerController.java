package com.kev.HotelManagementApplication.customer;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(path = "/allbookings/{customerid}")
    public CustomerBookingsDTO getCustomerBookingList
            (@PathVariable(name = "customerid") int customerId) {
        return customerService.getCustomerBookingList(customerId);
    }

}
