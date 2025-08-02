package com.akasaAir.utils;

import java.time.LocalDate;

public class DateAndTime {

    public static String getFutureDay(int daysToAdd) {
        return LocalDate.now().plusDays(daysToAdd).getDayOfMonth()+ "";
    }
}
