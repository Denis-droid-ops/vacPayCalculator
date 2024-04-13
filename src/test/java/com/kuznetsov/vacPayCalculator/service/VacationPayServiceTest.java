package com.kuznetsov.vacPayCalculator.service;

import com.kuznetsov.vacPayCalculator.dto.PersonVacationDataDto;
import com.kuznetsov.vacPayCalculator.exception.InvalidVacationDaysCountException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles(value = {"test"})
class VacationPayServiceTest {

    private final VacationPayService vacationPayService;
    private static final PersonVacationDataDto PERSON_VACATION_DATA_DTO =
            new PersonVacationDataDto(31000L,
                    28,
                    LocalDate.of(2024,2,20),
                    LocalDate.of(2024,3,4));

    VacationPayServiceTest(VacationPayService vacationPayService) {
        this.vacationPayService = vacationPayService;
    }

    @Test
    void calculacte() {
       Float actual = vacationPayService.calculacte(PERSON_VACATION_DATA_DTO.getAvgSalaryForYear(),
               PERSON_VACATION_DATA_DTO.getVacDaysCount());
        assertThat(actual).isEqualTo(25773.379f);
    }

    @Test
    void calculacteWithInvalidParams() {
        assertThrows(InvalidVacationDaysCountException.class,
                ()->vacationPayService.calculacte(PERSON_VACATION_DATA_DTO.getAvgSalaryForYear(),
                        PERSON_VACATION_DATA_DTO.getVacationStart(),
                        PERSON_VACATION_DATA_DTO.getVacationStart()));
    }

    @Test
    void сalculacteWithVacStartAndVacEndDates() {
        Float actual = vacationPayService.calculacte(PERSON_VACATION_DATA_DTO.getAvgSalaryForYear(),
                PERSON_VACATION_DATA_DTO.getVacationStart(),PERSON_VACATION_DATA_DTO.getVacationEnd());
        assertThat(actual).isEqualTo(11045.734f);
    }
}