package com.kev.HotelManagementApplication.booking;

import com.kev.HotelManagementApplication.factory.DTOFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingControllerTest {

    @Test
    void t7_when_NoBookings_Expect_EmptyListOfBookingDTO() {

        BookingService mockBookingService = mock(BookingService.class);
        when(mockBookingService.getBookingList())
                .thenReturn(new ArrayList<>());  //create empty booking list

        BookingController mockBookingController = mock(BookingController.class);
        List<BookingDTO> result = mockBookingController.getBookingList();

        assertEquals(0, result.size());
    }

    @Test
    void t8_when_GetBookingList_Expect_OneCallToBookingService() {

        BookingService mockBookingService = mock(BookingService.class);
        DTOFactory mockDTOFactory = mock(DTOFactory.class);

        BookingController bookingController = new BookingController
                (mockBookingService, mockDTOFactory);

        bookingController.getBookingList();

        verify(mockBookingService, times(1)).getBookingList();
        verifyNoMoreInteractions(mockBookingService);
    }

}