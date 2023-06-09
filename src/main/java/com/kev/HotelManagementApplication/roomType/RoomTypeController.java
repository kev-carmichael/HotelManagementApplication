package com.kev.HotelManagementApplication.roomType;


import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/roomtype")
@AllArgsConstructor
@Validated
public class RoomTypeController {
    private final RoomTypeService roomTypeService;

    @GetMapping(path="/all")
    public List<RoomTypeDTO> getRoomTypeList(){
        return roomTypeService.getRoomTypeList();
    }

    //need to add error msg if add type that already exists
    @PostMapping(path="/add/{type}")
    public RoomTypeDTO addRoomType(
            @PathVariable("type")
            @NotBlank(message = "RoomType type cannot be blank") String type) {
        return roomTypeService.addRoomType(type);
    }

    @DeleteMapping(path = "/delete/{id}")
    public boolean deleteRoomType(
            @PathVariable(name = "id")
            @Min(value = 1, message = "roomTypeId must be greater than zero") int id)
    {
        return roomTypeService.deleteRoomType(id);
    }

    @PostMapping(path = "/update/{id}/{type}")
    public RoomTypeDTO updateRoomType(
            @PathVariable(name = "id")
            @Min(value = 1, message = "roomTypeId must be greater than zero") int id,
            @PathVariable(name = "type")
            @NotBlank(message = "RoomType type cannot be blank") String type) {
        return roomTypeService.updateRoomType(id, type);
    }


}
