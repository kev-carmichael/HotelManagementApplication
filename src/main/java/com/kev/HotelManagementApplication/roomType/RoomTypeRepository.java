package com.kev.HotelManagementApplication.roomType;

import com.kev.HotelManagementApplication.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {



}
