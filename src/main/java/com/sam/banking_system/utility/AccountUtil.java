package com.sam.banking_system.utility;

import java.time.LocalDateTime;

public class AccountUtil {

    //generates account number with 10 digits
    public static String generateAccNumber(LocalDateTime localDateTime, Long userId){
        return String.format("%010d", localDateTime.getNano() + userId);
    }
}
