package com.kev.HotelManagementApplication.error;

import java.time.LocalDate;

public class DateInIsSameAsOrAfterDateOutException extends RuntimeException {

    public DateInIsSameAsOrAfterDateOutException() {
        super("Error - checkin date is the same as or after checkout date");
    }

}
