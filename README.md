**Процедура запуска тестов:**

1. Создать и запустить контейнер _travelapp_ на образе _mysql_;

2. Создать и запустить контейнер _gate-simulator_ на образе _node-app_;

3. Запустить приложение _aqa-shop.jar_ в папке _artifacts_:
3.1. Для запуска приложения с базой данных MySQL ввести команду java -jar -Dspring.config.location=mysql-app.
properties aqa-shop.jar &;
3.2. Для запуска приложения с базой данных PostgreSQL ввести команду java -jar -Dspring.config.location=
postgresql-app.properties aqa-shop.jar &;

4. Запустить тесты;

5. Запустить _allure_ репорт.