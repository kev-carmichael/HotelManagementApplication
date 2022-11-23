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
    //does room need to be linked to HOtel???? or just Hotel has collection of Rooms
    //must do otherwise if have two room 101's how do you know which hotel is belongs to?
    // maybe leave  Hotel till last, as extra layer of complexity

}
