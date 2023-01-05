package com.kev.HotelManagementApplication.factory;

import com.kev.HotelManagementApplication.customer.CustomerDTO;
import com.kev.HotelManagementApplication.entity.Customer;
import com.kev.HotelManagementApplication.entity.Room;
import com.kev.HotelManagementApplication.entity.RoomType;
import com.kev.HotelManagementApplication.room.RoomDTO;
import com.kev.HotelManagementApplication.roomType.RoomTypeDTO;
import org.springframework.stereotype.Component;

@Component
public class DTOFactory {

    public CustomerDTO createDTO(Customer customer) {
        CustomerDTO customerDTO =
                new CustomerDTO(
                        customer.getCustomerId(),
                        customer.getName(),
                        customer.getAddress().toString());
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


}
