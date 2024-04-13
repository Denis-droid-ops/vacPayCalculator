package com.kuznetsov.vacPayCalculator.handler;

import com.kuznetsov.vacPayCalculator.errorResponse.VacationPayErrorResponse;
import com.kuznetsov.vacPayCalculator.exception.InvalidVacationDaysCountException;
import com.kuznetsov.vacPayCalculator.exception.VacationPayValidationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages = "com.kuznetsov.vacPayCalculator.controller")
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<VacationPayErrorResponse> handleException(InvalidVacationDaysCountException ex){
        VacationPayErrorResponse vacationPayErrorResponse =
                new VacationPayErrorResponse(ex.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(vacationPayErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<VacationPayErrorResponse> handleException(VacationPayValidationException ex){
        StringBuilder sb = new StringBuilder("Error! ");
        ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .forEach(message->sb.append(message).append("   "));
        ex.getBindingResult().getGlobalErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .forEach(message->sb.append(message).append("   "));
        VacationPayErrorResponse vacationPayErrorResponse =
                new VacationPayErrorResponse(sb.toString(),System.currentTimeMillis());
        return new ResponseEntity<>(vacationPayErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
