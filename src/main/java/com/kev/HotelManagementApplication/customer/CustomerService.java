package com.kev.HotelManagementApplication.customer;


import com.kev.HotelManagementApplication.address.AddressRepository;
import com.kev.HotelManagementApplication.entity.Address;
import com.kev.HotelManagementApplication.entity.Customer;
import com.kev.HotelManagementApplication.error.DobInFutureException;
import com.kev.HotelManagementApplication.factory.DTOFactory;
import com.kev.HotelManagementApplication.util.StringHasher;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private DTOFactory dtoFactory;
    private final AddressRepository addressRepository;
    private final StringHasher stringHasher;

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

    public boolean dobIsValidDate(String dob) {
        //check if dob is after today's date
        LocalDate parsedDob = LocalDate.parse(dob);
        LocalDate today = LocalDate.now();
        if(parsedDob.isAfter(today)){
            return false;
        }
        return true;
    }

    public Customer createCustomer(String name, String dob, String streetNumber,
                                   String street, String town, String postcode) {

        //check if address already exists then create Address entity
        if(findIfAddressExists(streetNumber, postcode)) {
            return null;
        }
        Address address = new Address(0, streetNumber,
                street, town, postcode); //id=0, as id is auto generated
        addressRepository.save(address);

        //check if dob is after today's date
        if(!dobIsValidDate(dob)) {
            throw new DobInFutureException();
        }

        //check if customer name & dob combination already exists
        if(nameAndDobAlreadyExist(name, LocalDate.parse(dob))) {
            return null;
        }

        String token =
                stringHasher.hashString(
                        name + dob + ":" + LocalDateTime.now());

        int customerSize = customerRepository.findAll().size();
        Customer customer = new Customer(
                (customerSize + 1),
                name,
                LocalDate.parse(dob),
                address,
                null,
                token);
        return customerRepository.save(customer);
    }

    public Customer checkCustomerCredentials(String name, String dob) {
        Customer customer = customerRepository.findByName(name);
        if (customer != null && customer.getDob().toString().equals(dob)) { //add dob as well as name for verification
            String token = stringHasher.hashString
                    (customer.getName() + customer.getDob() + ":" + LocalDateTime.now());
            customer.setToken(token);
            customer = customerRepository.save(customer);
            return customer;
        }
        return null;
    }

    public Customer checkCustomerCredentials(String token) {
        Customer customer = customerRepository.findByToken(token);
        if (customer != null && customer.getToken() != null) {
            return customer;
        }
        return null;
    }

    public void clearToken(int id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer != null && customer.getToken() != null) {
            customer.setToken(null);
            customerRepository.save(customer);
        }
    }


}
