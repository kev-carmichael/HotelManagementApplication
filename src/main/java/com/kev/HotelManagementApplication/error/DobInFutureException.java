package com.kev.HotelManagementApplication.error;

import java.time.LocalDate;

public class DobInFutureException extends RuntimeException{

    public DobInFutureException() {
        super("Error - date of birth is beyond today's date");
    }
}
