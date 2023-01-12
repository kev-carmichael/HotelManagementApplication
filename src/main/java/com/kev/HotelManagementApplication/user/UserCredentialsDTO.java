package com.kev.HotelManagementApplication.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@Getter
public class UserCredentialsDTO {

    @NotBlank(message = "Name cannot be blank")
    private final String name;

}
