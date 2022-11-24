package com.kev.HotelManagementApplication.roomType;


import com.kev.HotelManagementApplication.entity.RoomType;
import com.kev.HotelManagementApplication.factory.DTOFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;
    private DTOFactory dtoFactory;

    public List<RoomTypeDTO> getRoomTypeList() {
        List<RoomTypeDTO> list = new ArrayList<>();
        for (RoomType roomType : roomTypeRepository.findAll()){
            RoomTypeDTO roomTypeDTO = dtoFactory.createDTO(roomType);
            list.add(roomTypeDTO);
        }
        return list;
    }

    public RoomTypeDTO addRoomType(String type){

        int size = roomTypeRepository.findAll().size();

        //is it ok for Service to know about RoomType entity for planned architecture?
        RoomType roomType = new RoomType((size+1), type);
        roomTypeRepository.save(roomType); //this is saving the new room type

        return dtoFactory.createDTO((size+1), type);
    }

}
