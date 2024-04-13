package com.kuznetsov.vacPayCalculator.service;

import com.kuznetsov.vacPayCalculator.exception.InvalidVacationDaysCountException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.util.List;

import static java.time.temporal.ChronoUnit.*;

@Service
public class VacationPayService {

    private static final List<MonthDay> holidaysList = List.of(
            MonthDay.of(Month.JANUARY,1),
            MonthDay.of(Month.JANUARY,2),
            MonthDay.of(Month.JANUARY,3),
            MonthDay.of(Month.JANUARY,4),
            MonthDay.of(Month.JANUARY,5),
            MonthDay.of(Month.JANUARY,6),
            MonthDay.of(Month.JANUARY,7),
            MonthDay.of(Month.JANUARY,8),
            MonthDay.of(Month.FEBRUARY,23),
            MonthDay.of(Month.MARCH,8),
            MonthDay.of(Month.MAY,1),
            MonthDay.of(Month.MAY,9),
            MonthDay.of(Month.JUNE,12),
            MonthDay.of(Month.NOVEMBER,4)
    );
    public Float calculacte(Long avgSalaryForYear, LocalDate vacationStart,
                           LocalDate vacationEnd) {
        long between  = DAYS.between(vacationStart, vacationEnd);

        if(between<=0){
           throw new InvalidVacationDaysCountException();
        }

        if(between>72){
            throw new InvalidVacationDaysCountException("Неверное количество отпускных дней! " +
                    "Количество отпускных дней не должно быть больше 72 по ТК РФ");
        }

        long vacationDays = vacationStart.datesUntil(vacationEnd)
                .filter(d-> !holidaysList.contains(MonthDay.of(d.getMonth(),d.getDayOfMonth())))
                .count();

        float avgSalaryForDay = avgSalaryForYear/29.3f;
        float tax = avgSalaryForDay*vacationDays*13/100;
        return avgSalaryForDay*vacationDays-tax;
    }

    public Float calculacte(Long avgSalaryForYear,Integer vacDaysCount){
        if(vacDaysCount<=0){
            throw new InvalidVacationDaysCountException();
        }
        float avgSalaryForDay = avgSalaryForYear/29.3f;
        float tax = avgSalaryForDay*vacDaysCount*13/100;
        return avgSalaryForDay*vacDaysCount-tax;
    }
}
