package com.kev.HotelManagementApplication.error;

import java.time.LocalDate;

public class DobInFutureException extends RuntimeException{

    public DobInFutureException(LocalDate dob) {
        super("Error - date of birth is beyond today's date");
    }
}
