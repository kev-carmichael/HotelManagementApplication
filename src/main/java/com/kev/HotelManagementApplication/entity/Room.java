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
@Table(name = "room")

public class Room {
    @Id
    private int roomId;
    private String roomNumber;

    @ManyToOne
    @JoinColumn(name = "room_type", nullable = false)
    private RoomType roomType;

    @Override
    public String toString()
    {
        return String.format(
                "%s, %s",
                roomNumber,
                roomType);
    }


}
