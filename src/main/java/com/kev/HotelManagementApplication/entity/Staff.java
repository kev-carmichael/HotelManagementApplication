package com.kev.HotelManagementApplication.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "staff")
public class Staff
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int staffId;

    private String email;
    private String name;
    private String password;
    private String token;
}
