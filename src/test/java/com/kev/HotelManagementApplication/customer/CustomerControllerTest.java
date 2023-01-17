package com.kev.HotelManagementApplication.customer;

import com.kev.HotelManagementApplication.error.DobInFutureException;
import com.kev.HotelManagementApplication.factory.DTOFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerControllerTest {

    @Test
    void t9_when_CustomerExists_Expect_NonNullCustomerBookingsDTO() {

        CustomerService mockCustomerService = mock(CustomerService.class);
        when(mockCustomerService.getCustomerBookingList(anyInt()))
                .thenReturn(mock(CustomerBookingsDTO.class));

        DTOFactory mockDTOFactory = mock(DTOFactory.class);


        CustomerController customerController = new CustomerController
                (mockCustomerService,mockDTOFactory);

        CustomerBookingsDTO result = customerController.getCustomerBookingList(anyInt());

        assertNotNull(result);
    }

    @Test
    void t10_when_CustomerDoesNotExist_Expect_NullResult() {

        CustomerService mockCustomerService = mock(CustomerService.class);
        when(mockCustomerService.getCustomerBookingList(anyInt()))
                .thenReturn(null);

        DTOFactory mockDTOFactory = mock(DTOFactory.class);


        CustomerController customerController = new CustomerController
                (mockCustomerService,mockDTOFactory);

        CustomerBookingsDTO result = customerController.getCustomerBookingList(anyInt());

        assertNull(result);
    }

}