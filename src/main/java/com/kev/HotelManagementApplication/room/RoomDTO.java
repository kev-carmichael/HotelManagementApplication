package com.kev.HotelManagementApplication.room;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
//@RequiredArgsConstructor
@Getter
@Setter

public class RoomDTO {
    @Min(value = 1, message = "roomId must be greater than zero")
    private final int roomId;

    @NotBlank(message = "roomNumber cannot be blank")
    private final String roomNumber;

    @NotBlank(message = "roomType cannot be blank")
    private final String roomType;
}
