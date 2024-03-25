# vacPayCalculator
project, that calculate vacation pays
----------------------------
## Что представляет собой этот проект?

Проект представляет собой web приложение с использованием Spring Boot, которое производит расчет отпускных денег

## Какие функции предоставляет?

Приложение предоставляет один API: GET "/calculacte", который непосредственно и производит расчет, 
в метод calculacte передаются данные в формате JSON в виде:

{

    "avgSalaryForYear": 28000, - Long type, средняя зарплата за 12 месяцев
    
    "vacDaysCount": 14, - Integer type, количество дней отпуска
    
    "vacationStart": "2024-02-20", - LocalDate type, начальный день отпуска
    
    "vacationEnd": "2024-03-05" - LocalDate type, конечный день отпуска
    
}

Метод расчитывает отпускные либо с определенным количеством дней(тогда параметры vacationStart и vacationEnd необязательны),
либо с конкретными календарными датами(тогда параметр vacDaysCount необязателен) с учетом ПРАЗДНИЧНЫХ дней

## Какие технологии, библиотеки использованы?

Java 17, Spring Boot, Maven, JUnit 5, AssertJ, Hibernate validator

## Тестирование

В проекте написаны юнит тесты для сервисного слоя

## Авторы
Telegram: @gilbertGrp

GitHub profile: https://github.com/Denis-droid-ops
