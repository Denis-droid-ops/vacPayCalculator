package com.kuznetsov.vacPayCalculator.exception;

import org.springframework.validation.BindingResult;

public class VacationPayValidationException extends RuntimeException{

    private BindingResult bindingResult;

    public VacationPayValidationException(BindingResult bindingResult){
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}
