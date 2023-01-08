package com.kev.HotelManagementApplication.customer;

import com.kev.HotelManagementApplication.factory.DTOFactory;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    private final DTOFactory dtoFactory;

    @GetMapping(path = "/all")
    public List<CustomerDTO> getCustomerList() {
        return customerService.getCustomerList();
    }

    @GetMapping(path = "/allbookings/{customerid}")
    public CustomerBookingsDTO getCustomerBookingList
            (@PathVariable(name = "customerid") int customerId) {
        return customerService.getCustomerBookingList(customerId);
    }

    @PostMapping(path = "/add/{name}/{dob}/{streetnumber}/{street}/{town}/{postcode}")
    public CustomerDTO addCustomer(@PathVariable("name") String name,
                                   @PathVariable("dob") String dob,
                                   @PathVariable("streetnumber") String streetNumber,
                                   @PathVariable("street") String street,
                                   @PathVariable("town") String town,
                                   @PathVariable("postcode") String postcode) {
        return dtoFactory.createDTOWithoutBookings(customerService.createCustomer
                (name, dob, streetNumber, street, town, postcode));
    }

}
