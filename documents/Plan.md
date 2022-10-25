# План тестирования

**Объект тестирования:** веб-сервис, взаимодействующий с СУБД и API Банка;

_Тестируемые функции:_
- Функция моментальной покупки;
- Функция покупки в кредит;
- Поддержка Postgresql базой данных;

_Тестируемые данные:_
- Банковская карта номер 4444 4444 4444 4441 со статусом APPROVED;
- Банковская карта номер 4444 4444 4444 4442 со статусом DECLINED;
- данные владельца карты, срока годности и CVV-код будут генерироваться Faker;

_Окружение тестирования:_
- Операционная система: MacOS Monterey версия 12.5
- IDE: IntelliJ IDEA 2022.2 (Community Edition)
- Java: openjdk 18.0.2 2022-07-19

_Тест-дизайн на валидность полей для заполнения данных карт на оплату_

| Проверка всех полей     | Тест-кейс 1 | Тест-кейс 2 | Тест-кейс 3 | Тест-кейс 4 | Тест-кейс 5 | Тест-кейс 6 | Тест-кейс 7 |
|-------------------------|-------------|-------------|-------------|-------------|-------------|-------------|-------------|
| Номер карты             | +           | -           | -           | +           | +           | +           | +           |
| Владелец                | +           | -           | +           | -           | +           | +           | +           |
| Месяц (неверный формат) | +           | -           | +           | +           | -           | +           | +           |
| Год (неверный формат)   | +           | -           | +           | +           | +           | -           | +           |
| CVV-код                 | +           | -           | +           | +           | +           | +           | -           |
> где “+” значит валидное значение, “-” невалидное;

Также на месяц и год необходимо по отдельности провести проверки на истёкший
срок карты и на неверно указанный срок (больше 5 лет). Это будет еще по 4
тест-кейса на валидность значений на каждую из 2 функций сервиса (суммарно
8 тестов).

Всего тестов на валидность значений будет 22 - по 11 на каждую функцию оплаты
методом эквивалентных значений.

_Тест-дизайн на проверку ответов от баз данных MySQL и PostgreSQL_

| База данных/Тестируемая функция | Покупка | Покупка в кредит |
|---------------------------------|---------|------------------|
| MySQL                           | +       | +                |
| PostgreSQL                      | +       | +                |

Для каждой тестируемой функции будет проведен тест на получение ответа от 
обоих типов баз данных. 


**Предварительные сроки осуществления тестирования:**

Автоматическое тестирование будет завершено к 03 ноября 2022 года.

**Предварительные результаты тестирования:**

Для каждой базы данных будет выполнено по 24 тест-кейса:

- 22 теста на валидность заполняемых полей (из них 8 тестов будут 
произведены по позитивному сценарию, и 18 - по негативному);
- 2 теста на получение ответа от баз данных (по позитивному сценарию).

Всего тестов получится 48.