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
        for (RoomType roomType : roomTypeRepository.findAll()) {
            RoomTypeDTO roomTypeDTO = dtoFactory.createDTO(roomType);
            list.add(roomTypeDTO);
        }
        return list;
    }

    public RoomTypeDTO addRoomType(String type) {

        int size = roomTypeRepository.findAll().size();

        RoomType roomType = new RoomType((size + 1), type);
        roomTypeRepository.save(roomType);

        return dtoFactory.createDTO((size + 1), type);
    }

    public RoomTypeDTO updateRoomType(int roomTypeId, String type) {

        Optional<RoomType> originalRoomTypeDTO = roomTypeRepository.findById(roomTypeId);

        if (!originalRoomTypeDTO.isPresent()) {
            return null;
        }

        RoomType roomTypeEntity = originalRoomTypeDTO.get();
        roomTypeEntity.setType(type);
        roomTypeRepository.save(roomTypeEntity);

        return dtoFactory.createDTO(roomTypeEntity);
    }

    public boolean deleteRoomType(int id) {
        if (roomTypeRepository.existsById(id)) {
            try {
                roomTypeRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                e.printStackTrace(System.err);
                return false;
            }
        }
        return false;
    }


}
