package com.kev.HotelManagementApplication.address;

import com.kev.HotelManagementApplication.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository   extends JpaRepository<Address, Integer> {

}
