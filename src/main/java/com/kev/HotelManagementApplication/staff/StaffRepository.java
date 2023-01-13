package com.kev.HotelManagementApplication.staff;

import com.kev.HotelManagementApplication.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    Staff findByEmail(String email);
}
