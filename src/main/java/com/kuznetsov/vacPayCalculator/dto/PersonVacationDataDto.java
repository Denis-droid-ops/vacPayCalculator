package com.kuznetsov.vacPayCalculator.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


public final class PersonVacationDataDto {

    @Min(value = 19242,message = "Значение avgSalaryForYear не может быть ниже прожиточного минимума!")
    @NotNull(message = "Значение avgSalaryForYear не может быть пустым!")
    private final Long avgSalaryForYear;

    @Min(value = 1,message = "Значение vacDaysCount не может быть меньше 1!")
    @Max(value = 72,message = "Значение vacDaysCount не может быть больше 72 по ТК РФ!")
    private final Integer vacDaysCount;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate vacationStart;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate vacationEnd;

    public PersonVacationDataDto(Long avgSalaryForYear, Integer vacDaysCount, LocalDate vacationStart, LocalDate vacationEnd) {
        this.avgSalaryForYear = avgSalaryForYear;
        this.vacDaysCount = vacDaysCount;
        this.vacationStart = vacationStart;
        this.vacationEnd = vacationEnd;
    }

    public Long getAvgSalaryForYear() {
        return avgSalaryForYear;
    }

    public Integer getVacDaysCount() {
        return vacDaysCount;
    }

    public LocalDate getVacationStart() {
        return vacationStart;
    }

    public LocalDate getVacationEnd() {
        return vacationEnd;
    }
}
