package com.kev.HotelManagementApplication.factory;

import com.kev.HotelManagementApplication.booking.BookingDTO;
import com.kev.HotelManagementApplication.customer.CustomerBookingsDTO;
import com.kev.HotelManagementApplication.customer.CustomerDTO;
import com.kev.HotelManagementApplication.customer.CustomerRepository;
import com.kev.HotelManagementApplication.entity.*;
import com.kev.HotelManagementApplication.room.RoomDTO;
import com.kev.HotelManagementApplication.roomType.RoomTypeDTO;
import com.kev.HotelManagementApplication.staff.StaffDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Component
public class DTOFactory {
    private final CustomerRepository customerRepository;

    public DTOFactory(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDTO createDTO(Customer customer) {
        try {
            CustomerDTO customerDTO =
                    new CustomerDTO(
                            customer.getCustomerId(),
                            customer.getName(),
                            customer.getDob(),
                            customer.getAddress().toString());
            customerDTO.setNumberOfBookings(customer.getBookingCount());
            customerDTO.setBookings(createDTOList(customer.getBookings()));
            customer.getToken();
            return customerDTO;
        } catch (NoSuchElementException e) {
            return createDTOWithoutBookings(customer);
        }
    }

    public CustomerDTO createDTOWithoutBookings(Customer customer) {
        CustomerDTO customerDTO =
                new CustomerDTO(
                        customer.getCustomerId(),
                        customer.getName(),
                        customer.getDob(),
                        customer.getAddress().toString());
        return customerDTO;
    }

    public CustomerBookingsDTO createCustomerBookingsDTO(Customer customer) {
        CustomerBookingsDTO customerBookingsDTO =
                new CustomerBookingsDTO(createDTOList(customer.getBookings()));
        return customerBookingsDTO;
    }


    public List<BookingDTO> createDTOList(List<Booking> bookings) {
        CustomerDTO customerDTO = createSummaryDTO(bookings.stream().findFirst().get().getCustomer());
        return bookings.stream().map(booking -> createDTO(booking, customerDTO)).collect(Collectors.toCollection(ArrayList::new));
    }

    public BookingDTO createDTO(Booking booking, CustomerDTO customerDTO) {
        return new BookingDTO(booking.getBookingId(), booking.getDateIn(),
                booking.getDateOut(), booking.getCustomer().toString(),
                booking.getRoom().toString());
    }

    public CustomerDTO createSummaryDTO(Customer customer) {
        CustomerDTO customerDTO =
                new CustomerDTO(
                        customer.getCustomerId(),
                        customer.getName(),
                        customer.getDob(),
                        customer.getAddress().toString());
                customerDTO.setNumberOfBookings(customer.getBookingCount());

                return customerDTO;
    }

    public RoomDTO createDTO(Room room) {
        RoomDTO roomDTO =
                new RoomDTO(
                        room.getRoomId(),
                        room.getRoomNumber(),
                        room.getRoomType().toString());
        return roomDTO;
    }

    public RoomTypeDTO createDTO(RoomType roomType) {
        RoomTypeDTO roomTypeDTO =
                new RoomTypeDTO(
                        roomType.getRoomTypeId(),
                        roomType.getType());
        return roomTypeDTO;
    }

    public RoomTypeDTO createDTO(int roomTypeId, String type) {

        RoomTypeDTO roomTypeDTO =
                new RoomTypeDTO(roomTypeId, type);
        return roomTypeDTO;
    }

    public BookingDTO createDTO(Booking booking) {
        BookingDTO bookingDTO =
                new BookingDTO(
                        booking.getBookingId(),
                        booking.getDateIn(),
                        booking.getDateOut(),
                        booking.getCustomer().toString(),
                        booking.getRoom().toString());
        return bookingDTO;
    }

    public CustomerDTO createDTOWithToken(Customer customer) {
        if (customer == null)
        {
            return null;
        }

        CustomerDTO customerDTO =
                new CustomerDTO(
                        customer.getCustomerId(),
                        customer.getName(),
                        customer.getDob(),
                        customer.getAddress().toString(),
                        0,
                        null,
                        customer.getToken());

        return customerDTO;
    }

    public StaffDTO create(Staff staff)
    {
        if (staff == null)
        {
            return null;
        }

        StaffDTO staffDTO =
                new StaffDTO(
                        staff.getStaffId(),
                        staff.getEmail(),
                        staff.getName(),
                        staff.getToken());

        return staffDTO;
    }



}
