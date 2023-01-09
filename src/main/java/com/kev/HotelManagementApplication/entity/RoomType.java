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
@Table(name="room_type")
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomTypeId;
    private String type; //substitute for enum?
    @Override
    public String toString()
    {
        return String.format(
                "%s",
                type);
    }

}
