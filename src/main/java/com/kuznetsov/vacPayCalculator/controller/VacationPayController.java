package com.kuznetsov.vacPayCalculator.controller;

import com.kuznetsov.vacPayCalculator.dto.PersonVacationDataDto;
import com.kuznetsov.vacPayCalculator.exception.VacationPayValidationException;
import com.kuznetsov.vacPayCalculator.service.VacationPayService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class VacationPayController {

    private final VacationPayService vacationPayService;

    @Autowired
    public VacationPayController(VacationPayService vacationPayService) {
        this.vacationPayService = vacationPayService;
    }


    @GetMapping("/calculacte")
    public ResponseEntity<Float> calculacte(@Valid PersonVacationDataDto personVacationDataDto,
                                            BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new VacationPayValidationException(bindingResult);
        }
        if(personVacationDataDto.getVacDaysCount()!=null){
            return ResponseEntity.ok(vacationPayService.calculacte(personVacationDataDto.getAvgSalaryForYear(),
                    personVacationDataDto.getVacDaysCount()));
        }else {
            if(personVacationDataDto.getVacationStart()==null &&
                    personVacationDataDto.getVacationEnd()==null){
                bindingResult.addError(new ObjectError("vacDaysCount, vacationStart, vacationEnd",
                        "Значения vacDaysCount, vacationStart, vacationEnd пусты!"));
                throw new VacationPayValidationException(bindingResult);
            }
            if(personVacationDataDto.getVacationStart()==null){
                bindingResult.rejectValue("vacationStart","",
                        "Значение vacationStart не может быть пустым!");
                throw new VacationPayValidationException(bindingResult);
            }
            if(personVacationDataDto.getVacationEnd()==null){
                bindingResult.rejectValue("vacationEnd","",
                        "Значение vacationEnd не может быть пустым!");
                throw new VacationPayValidationException(bindingResult);
            }
        }
        return ResponseEntity
                .ok(vacationPayService.calculacte(personVacationDataDto.getAvgSalaryForYear(),
                        personVacationDataDto.getVacationStart(),personVacationDataDto.getVacationEnd()));

    }
}
