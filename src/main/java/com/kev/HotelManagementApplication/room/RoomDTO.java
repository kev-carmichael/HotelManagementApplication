package com.kev.HotelManagementApplication.room;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
//@RequiredArgsConstructor
@Getter
@Setter

public class RoomDTO {
    private final int roomId;
    private final String roomNumber;
}
