package com.kev.HotelManagementApplication.staff;

import com.kev.HotelManagementApplication.entity.Staff;
import com.kev.HotelManagementApplication.util.StringHasher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StaffService
{
    private final StaffRepository staffRepository;
    private final StringHasher stringHasher;

    public Staff checkCredentials(String email, String password)
    {
        Staff staff = staffRepository.findByEmail(email);

        if (staff != null && staff.getPassword().equals(password))
        {
            String token =
                    stringHasher.hashString(
                            staff.getEmail() + ":" + LocalDateTime.now());
            staff.setToken(token);
            staff = staffRepository.save(staff);
            return staff;
        }
        return null;
    }

    public Staff checkCredentials(String token) {
        Staff staff = staffRepository.findByToken(token);
        if (staff != null && staff.getToken() != null)
        {
            return staff;
        }
        return null;
    }

}
