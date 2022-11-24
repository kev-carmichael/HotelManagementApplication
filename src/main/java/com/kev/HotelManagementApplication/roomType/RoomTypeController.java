package com.kev.HotelManagementApplication.roomType;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roomtype")
@AllArgsConstructor
public class RoomTypeController {
    private final RoomTypeService roomTypeService;

    //could be a needless use case - consider removal if have another 12
    @GetMapping(path="/all")
    public List<RoomTypeDTO> getRoomTypeList(){
        return roomTypeService.getRoomTypeList();
    }

    //need to add error msg if adding type that already exists
    @PostMapping(path="/add/{type}")
    public RoomTypeDTO addRoomType(@PathVariable("type") String type){
        return roomTypeService.addRoomType(type);
    }

}
