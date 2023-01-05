package com.kev.HotelManagementApplication.booking;

import com.kev.HotelManagementApplication.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
