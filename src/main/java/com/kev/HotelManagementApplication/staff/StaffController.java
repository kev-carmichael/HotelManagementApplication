package com.kev.HotelManagementApplication.staff;

import com.kev.HotelManagementApplication.factory.DTOFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor

@RestController
@RequestMapping(path = "/staff")
@Validated
public class StaffController
{
    private final StaffService staffService;
    private final DTOFactory dtoFactory;

    @PostMapping(path = "/checkcredentials/={email}/={password}")
    public StaffDTO checkCredentials
            (@PathVariable("email") @Email(message = "Email is not in the correct format") String email,
             @PathVariable("password") @NotBlank(message = "Password cannot be blank") String password) {
        return dtoFactory.create(staffService.checkCredentials(email, password));
    }

    @PostMapping(path = "/logout/={id}/={token}")
    public void logOut(
            @PathVariable(name = "id")
            @Min(value = 1, message = "staffId must be greater than zero") int id,
            @PathVariable(name = "token") String token) {
        staffService.clearToken(id);
    }


}
