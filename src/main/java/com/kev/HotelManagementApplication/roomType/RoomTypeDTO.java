package com.kev.HotelManagementApplication.roomType;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
//@RequiredArgsConstructor
@Getter
@Setter

public class RoomTypeDTO {
    @Min(value = 1, message = "roomTypeId must be greater than zero")
    private final int roomTypeId;

    @NotBlank(message = "RoomType type cannot be blank")
    private final String type;
}
