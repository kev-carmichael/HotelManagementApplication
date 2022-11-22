package com.kev.HotelManagementApplication.room;


import com.kev.HotelManagementApplication.customer.CustomerDTO;
import com.kev.HotelManagementApplication.entity.Customer;
import com.kev.HotelManagementApplication.entity.Room;
import com.kev.HotelManagementApplication.factory.DTOFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private DTOFactory dtoFactory;

    public List<RoomDTO> getRoomList() {
        List<RoomDTO> list = new ArrayList<>();
        for (Room room : roomRepository.findAll()){
            RoomDTO roomDTO = dtoFactory.createDTO(room);
            list.add(roomDTO);
        }
        return list;
    }

}
