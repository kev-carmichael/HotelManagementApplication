package com.kev.HotelManagementApplication.factory;

import com.kev.HotelManagementApplication.booking.BookingDTO;
import com.kev.HotelManagementApplication.customer.CustomerDTO;
import com.kev.HotelManagementApplication.entity.Booking;
import com.kev.HotelManagementApplication.entity.Customer;
import com.kev.HotelManagementApplication.entity.Room;
import com.kev.HotelManagementApplication.entity.RoomType;
import com.kev.HotelManagementApplication.room.RoomDTO;
import com.kev.HotelManagementApplication.roomType.RoomTypeDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DTOFactory {

    public CustomerDTO createDTO(Customer customer) {
        CustomerDTO customerDTO =
                new CustomerDTO(
                        customer.getCustomerId(),
                        customer.getName(),
                        customer.getAddress().toString());
                        customerDTO.setNumberOfBookings(customer.getBookingCount());
                        customerDTO.setBookings(createDTOList(customer.getBookings()));
        return customerDTO;
    }

    public List<BookingDTO> createDTOList(List<Booking> bookings) {
        CustomerDTO customerDTO = createSummaryDTO(bookings.stream().findFirst().get().getCustomer());
        return bookings.stream().map(booking -> createDTO(booking, customerDTO)).collect(Collectors.toCollection(ArrayList::new));
    }

    public BookingDTO createDTO(Booking booking, CustomerDTO customerDTO) {
        return new BookingDTO(booking.getBookingId(), booking.getDateIn(),
                booking.getDateOut(), booking.getCustomer().toString());
    }

    public CustomerDTO createSummaryDTO(Customer customer) {
        CustomerDTO customerDTO =
                new CustomerDTO(
                        customer.getCustomerId(),
                        customer.getName(),
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
                        booking.getCustomer().toString());
        return bookingDTO;
    }



}
