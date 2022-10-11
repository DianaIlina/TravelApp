**Процедура запуска тестов:**

1. Создать и запустить контейнер _travelapp_ на образе _mysql_;

2. Создать и запустить контейнер _gate-simulator_ на образе _node-app_;

3. Запустить приложение _aqa-shop.jar_ в папке _artifacts_:
3.1. Для запуска приложения с базой данных MySQL ввести команду java -jar -Dspring.config.location=application-mysql.properties aqa-shop.jar &;
3.2. Для запуска приложения с базой данных PostgreSQL ввести команду java -jar -Dspring.config.location=postgresql-app.properties aqa-shop.jar &;

4. Запустить тесты;

5. Запустить _allure_ репорт.

java -jar -Dspring.config.additional-location=application-mysql.properties artifacts/aqa-shop.jar
./gradlew test -Durl=jdbc:mysql://localhost:3306/app -Dusername=app -Dpassword=pass

```
+-----------------------+
| Tables_in_app         |
+-----------------------+
| credit_request_entity |
| order_entity          |
| payment_entity        |
+-----------------------+
```