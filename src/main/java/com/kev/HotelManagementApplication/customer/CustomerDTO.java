package com.kev.HotelManagementApplication.customer;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
//@RequiredArgsConstructor
@Getter
@Setter

public class CustomerDTO {
    private final int id;
    private final String name;
    private final String address;
}
