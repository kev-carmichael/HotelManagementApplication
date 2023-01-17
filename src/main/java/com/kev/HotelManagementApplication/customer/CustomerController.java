package com.kev.HotelManagementApplication.customer;

import com.kev.HotelManagementApplication.factory.DTOFactory;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
@Validated
public class CustomerController {

    private final CustomerService customerService;

    private final DTOFactory dtoFactory;

    @GetMapping(path = "/all")
    public List<CustomerDTO> getCustomerList() {
        return customerService.getCustomerList();
    }

    @GetMapping(path = "/allbookings/{customerid}")
    public CustomerBookingsDTO getCustomerBookingList
            (@PathVariable(name = "customerid")
             @Min(value = 1, message = "customerId must be greater than zero") int customerId) {
        return customerService.getCustomerBookingList(customerId);
    }

    @PostMapping(path = "/add/{name}/{dob}/{streetnumber}/{street}/{town}/{postcode}")
    public CustomerDTO addCustomer
            (@PathVariable("name") @NotBlank(message = "Name cannot be blank") String name,
             @PathVariable("dob")  @NotBlank(message = "dob cannot be blank") String dob,
             @PathVariable("streetnumber") @NotBlank(message = "streetNumber cannot be blank") String streetNumber,
             @PathVariable("street") @NotBlank(message = "street cannot be blank") String street,
             @PathVariable("town") @NotBlank(message = "town cannot be blank") String town,
             @PathVariable("postcode") @NotBlank(message = "postcode cannot be blank") String postcode) {
        return dtoFactory.createDTOWithoutBookings(customerService.createCustomer
                (name, dob, streetNumber, street, town, postcode));
    }

    @PostMapping(path = "/checkcredentials/{name}/{dob}")
    public CustomerDTO checkCustomerCredentials
            (@PathVariable("name") @NotBlank(message = "Name cannot be blank") String name,
             @PathVariable("dob")  @NotBlank(message = "dob cannot be blank") String dob) {
        return dtoFactory.createDTOWithToken(customerService.checkCustomerCredentials(name, dob));
    }

    @PostMapping(path = "/logout/{id}")
    public void logOut(
            @PathVariable(name = "id")
            @Min(value = 1, message = "customerId must be greater than zero") int id) {
        customerService.clearToken(id);
    }

}
