package com.kev.HotelManagementApplication.roomType;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
//@RequiredArgsConstructor
@Getter
@Setter

public class RoomTypeDTO {
    private final int roomTypeId;
    private final String type;
}
