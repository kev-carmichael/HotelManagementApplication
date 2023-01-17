package com.kev.HotelManagementApplication.customer;

import com.kev.HotelManagementApplication.address.AddressRepository;
import com.kev.HotelManagementApplication.entity.Address;
import com.kev.HotelManagementApplication.entity.Customer;
import com.kev.HotelManagementApplication.error.DobInFutureException;
import com.kev.HotelManagementApplication.factory.DTOFactory;
import com.kev.HotelManagementApplication.util.StringHasher;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class CustomerServiceTest {

    @Test
    void t1_when_CustomerEntityAddedHasADobAfterCurrentDate_Expect_DobInFutureException() {

        AddressRepository mockAddressRepository = mock(AddressRepository.class);
        CustomerRepository mockCustomerRepository = mock(CustomerRepository.class);
        DTOFactory mockDTOFactory = mock(DTOFactory.class);
        StringHasher mockStringHasher = mock(StringHasher.class);

        CustomerService customerService = new CustomerService(mockCustomerRepository,
                mockDTOFactory, mockAddressRepository, mockStringHasher);

        assertThrows(
                DobInFutureException.class,
                ()->customerService.createCustomer("Master A Test", "2033-01-08",
                        "1", "Test Street", "Test Town", "T35TY")
        );
    }

    @Test
    void t12_when_CustomerEntityAddedHasADobBeforeCurrentDate_Expect_NoDobInFutureException() {

        AddressRepository mockAddressRepository = mock(AddressRepository.class);
        when(mockAddressRepository.findAll())
                .thenReturn(new ArrayList<>());

        CustomerRepository mockCustomerRepository = mock(CustomerRepository.class);
        when(mockCustomerRepository.findAll())
                .thenReturn(new ArrayList<>());

        DTOFactory mockDTOFactory = mock(DTOFactory.class);
        StringHasher mockStringHasher = mock(StringHasher.class);

        CustomerService customerService = new CustomerService(mockCustomerRepository,
                mockDTOFactory, mockAddressRepository, mockStringHasher);

        assertDoesNotThrow(() -> customerService.createCustomer("Master A Test", "2010-10-10",
                "1", "Test Street", "Test Town", "T35TY"));
    }


}