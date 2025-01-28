package com.bs.spring.common.error;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MyException extends RuntimeException {

    private LocalDate date;


    public MyException(String message) {
        super(message);
        date=LocalDate.now();
    }



}
