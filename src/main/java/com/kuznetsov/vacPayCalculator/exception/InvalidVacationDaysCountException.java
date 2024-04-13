package com.kuznetsov.vacPayCalculator.exception;

public class InvalidVacationDaysCountException extends RuntimeException{
    public InvalidVacationDaysCountException(){
        super("Неверное количество отпускных дней! " +
                "Количество отпускных дней не должно быть меньше 1 дня");
    }

    public InvalidVacationDaysCountException(String message){
        super(message);
    }
}
