package com.kev.HotelManagementApplication.booking;

import com.kev.HotelManagementApplication.address.AddressRepository;
import com.kev.HotelManagementApplication.customer.CustomerDTO;
import com.kev.HotelManagementApplication.customer.CustomerRepository;
import com.kev.HotelManagementApplication.customer.CustomerService;
import com.kev.HotelManagementApplication.entity.Booking;
import com.kev.HotelManagementApplication.entity.Customer;
import com.kev.HotelManagementApplication.entity.Room;
import com.kev.HotelManagementApplication.error.DateInIsSameAsOrAfterDateOutException;
import com.kev.HotelManagementApplication.error.DobInFutureException;
import com.kev.HotelManagementApplication.factory.DTOFactory;
import com.kev.HotelManagementApplication.room.RoomRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookingServiceTest {

    @Test
    void t2_when_BookingEntityAddedHasADateInOnOrAfterDateOut_Expect_DateInIsSameAsOrAfterDateOutException() {

        BookingRepository mockBookingRepository = mock(BookingRepository.class);
        DTOFactory mockDTOFactory = mock(DTOFactory.class);
        CustomerRepository mockCustomerRepository = mock(CustomerRepository.class);
        RoomRepository mockRoomRepository = mock(RoomRepository.class);

        BookingService bookingService = new BookingService(mockBookingRepository,
                mockDTOFactory,mockCustomerRepository, mockRoomRepository);

        assertThrows(
                DateInIsSameAsOrAfterDateOutException.class,
                ()->bookingService.createBooking(1,"2023-12-11", "2023-02-01",
                         1));
    }

    @Test
    void t3_when_BookingEntityAddedHasANonValidCustomerId_Expect_NullResult() {

        BookingRepository mockBookingRepository = mock(BookingRepository.class);
        DTOFactory mockDTOFactory = mock(DTOFactory.class);
        CustomerRepository mockCustomerRepository = mock(CustomerRepository.class);
        RoomRepository mockRoomRepository = mock(RoomRepository.class);

        BookingService bookingService = new BookingService(mockBookingRepository,
                mockDTOFactory,mockCustomerRepository, mockRoomRepository);

        assertNull(bookingService.createBooking(-1, "2023-12-11", "2024-02-01",
                1));
    }

    @Test
    void t4_when_ThreeBookingEntities_Expect_GetBookingListReturnsListOfThreeBookingDTOs() {

        List<Booking> bookings = Arrays.asList(
                mock(Booking.class),
                mock(Booking.class),
                mock(Booking.class));

        BookingRepository mockBookingRepository = mock(BookingRepository.class);
        when(mockBookingRepository.findAll()).thenReturn(bookings);

        DTOFactory mockDTOFactory = mock(DTOFactory.class);
        when(mockDTOFactory.createDTO(any(Booking.class))).thenReturn(mock(BookingDTO.class));

        CustomerRepository mockCustomerRepository = mock(CustomerRepository.class);
        RoomRepository mockRoomRepository = mock(RoomRepository.class);

        BookingService bookingService = new BookingService(mockBookingRepository,
                mockDTOFactory,mockCustomerRepository, mockRoomRepository);

        List<BookingDTO> result = bookingService.getBookingList();

        assertEquals(3, result.size());

    }

    @Test
    void t11_when_BookingEntityAddedHasADateInBeforeDateOut_Expect_BookingCreated() {
        DTOFactory mockDTOFactory = mock(DTOFactory.class);

        CustomerRepository mockCustomerRepository = mock(CustomerRepository.class);
        when(mockCustomerRepository.findById(anyInt())).thenReturn(Optional.of(mock(Customer.class)));

        RoomRepository mockRoomRepository = mock(RoomRepository.class);
        when(mockRoomRepository.findById(anyInt())).thenReturn(Optional.of(mock(Room.class)));

        BookingRepository mockBookingRepository = mock(BookingRepository.class);
        when(mockBookingRepository.save(any(Booking.class))).thenReturn(mock(Booking.class));

        BookingService bookingService = new BookingService(mockBookingRepository,
                mockDTOFactory,mockCustomerRepository, mockRoomRepository);

        assertNotNull(bookingService.createBooking(1, "2023-02-01", "2023-12-11",
                1));
    }
    
}