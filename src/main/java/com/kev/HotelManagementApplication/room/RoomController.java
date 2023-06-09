package com.kev.HotelManagementApplication.room;


import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/room")
@AllArgsConstructor
@Validated
public class RoomController {
    private final RoomService roomService;

    @GetMapping(path="/all")
    public List<RoomDTO> getRoomList() {
        return roomService.getRoomList();
    }

}
