package com.kev.HotelManagementApplication.roomType;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roomtype")
@AllArgsConstructor
public class RoomTypeController {
    private final RoomTypeService roomTypeService;

    @GetMapping(path="/all")
    public List<RoomTypeDTO> getRoomTypeList(){
        return roomTypeService.getRoomTypeList();
    }
}
