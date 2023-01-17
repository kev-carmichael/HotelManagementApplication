package com.kev.HotelManagementApplication.roomType;

import com.kev.HotelManagementApplication.entity.RoomType;
import com.kev.HotelManagementApplication.factory.DTOFactory;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RoomTypeServiceTest {

    @Test
    void t5_when_DeleteRoomTypeWithNonValidId_Expect_ReturnFalse() {
        RoomTypeRepository mockRoomTypeRepository = mock(RoomTypeRepository.class);
        DTOFactory mockDTOFactory = mock(DTOFactory.class);

        RoomTypeService roomTypeService = new RoomTypeService(mockRoomTypeRepository,
                mockDTOFactory);

        assertFalse(roomTypeService.deleteRoomType(-1));

    }

    @Test  //an example of state verification, as per lecture notes 6c
    void t6_when_RoomTypeIdExists_Expect_UpdateRoomTypeReturnsNonNullRoomTypeDTO() {

        RoomTypeRepository mockRoomTypeRepository = mock(RoomTypeRepository.class);
        when(mockRoomTypeRepository.findById(anyInt()))
                .thenReturn(Optional.of(mock(RoomType.class)));
        when(mockRoomTypeRepository.save(mock(RoomType.class)))
                .thenReturn(mock(RoomType.class));

        DTOFactory mockDTOFactory = mock(DTOFactory.class);
        when(mockDTOFactory.createDTO(any(RoomType.class)))
                .thenReturn(mock(RoomTypeDTO.class));

        RoomType mockRoomType = mock(RoomType.class);
        String type = "AType";
        mockRoomType.setType(type);

        RoomTypeService roomTypeService = new RoomTypeService(mockRoomTypeRepository,
                mockDTOFactory);

        RoomTypeDTO result = roomTypeService.updateRoomType(anyInt(), type);

        assertNotNull(result);
    }

}