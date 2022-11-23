package com.kev.HotelManagementApplication.roomType;


import com.kev.HotelManagementApplication.entity.RoomType;
import com.kev.HotelManagementApplication.factory.DTOFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

}
