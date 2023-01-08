package com.kev.HotelManagementApplication.customer;


import com.kev.HotelManagementApplication.address.AddressRepository;
import com.kev.HotelManagementApplication.entity.Address;
import com.kev.HotelManagementApplication.entity.Customer;
import com.kev.HotelManagementApplication.factory.DTOFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private DTOFactory dtoFactory;
    private final AddressRepository addressRepository;

    public List<CustomerDTO> getCustomerList() {
        List<CustomerDTO> list = new ArrayList<>();
        for (Customer customer : customerRepository.findAll()){
            CustomerDTO customerDTO = dtoFactory.createDTO(customer);
            list.add(customerDTO);
        }
        return list;
    }

    public CustomerBookingsDTO getCustomerBookingList(int customerId) {
        for (Customer customer : customerRepository.findAll()){
            if(customer.getCustomerId() == customerId){
                CustomerBookingsDTO customerBookingsDTO =
                        dtoFactory.createCustomerBookingsDTO(customer);
                return customerBookingsDTO;
            }
        }
        return null;
    }

    public boolean findIfAddressExists(String streetNumber, String postcode) {
        for (Address address : addressRepository.findAll()){
            if(address.getStreetNumber() == streetNumber &&
                    address.getPostcode() == postcode) {
                return true;
            }
        }
        return false;
    }

    public boolean nameAndDobAlreadyExist(String name, LocalDate parsedDob) {
        for (Customer customer : customerRepository.findAll()){
            if(customer.getName() == name &&
                    customer.getDob() == parsedDob) {
                return true;
            }
        }
        return false;
    }


    public Customer createCustomer(String name, String dob, String streetNumber,
                                   String street, String town, String postcode) {

        //check if address already exists then create Address entity
        if(findIfAddressExists(streetNumber, postcode)) {
            return null;
        }
        int addressSize = addressRepository.findAll().size();
        Address address = new Address(addressSize + 1, streetNumber,
                street, town, postcode);
        addressRepository.save(address);

        //check if customer name & dob combination already exists
        LocalDate parsedDob = LocalDate.parse(dob);
        if(nameAndDobAlreadyExist(name, parsedDob)) {
            return null;
        }

        int customerSize = customerRepository.findAll().size();
        Customer customer = new Customer(
                (customerSize + 1),
                name,
                parsedDob,
                address,
                null);
        return customerRepository.save(customer);
    }


}
