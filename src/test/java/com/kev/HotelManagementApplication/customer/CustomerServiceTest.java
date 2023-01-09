package com.kev.HotelManagementApplication.customer;

import com.kev.HotelManagementApplication.address.AddressRepository;
import com.kev.HotelManagementApplication.entity.Address;
import com.kev.HotelManagementApplication.entity.Customer;
import com.kev.HotelManagementApplication.error.DobInFutureException;
import com.kev.HotelManagementApplication.factory.DTOFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class CustomerServiceTest {

    @Test
    void t4_when_CustomerEntityAddedHasADobAfterCurrentDate_Expect_DobInFutureException() {

        LocalDate dobFuture = LocalDate.now().plusYears(10);

        AddressRepository mockAddressRepository = mock(AddressRepository.class);
        CustomerRepository mockCustomerRepository = mock(CustomerRepository.class);
        DTOFactory mockDTOFactory = mock(DTOFactory.class);

        CustomerService customerService = new CustomerService(mockCustomerRepository,
                mockDTOFactory, mockAddressRepository);

        assertThrows(
                DobInFutureException.class,
                ()->customerService.createCustomer("Master A Test", "2033-01-08",
                        "1", "Test Street", "Test Town", "T35TY")
        );
    }

}