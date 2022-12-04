# finances

Перед первым запуском необходимо создать базу данных PostgreSQL

В файле `src/main/resources/application.properties` установить параметры для подключения к базе данных:  
`spring.datasource.url`  
`spring.datasource.username`  
`spring.datasource.password`  

# REST API

## 1. Получить информацию обо всех клиентах

### Пример запроса

`GET http://localhost:8080/client`

### Ответ

```json
[{"id":1,"lastName":"Nunquam","firstName":"Ridetis","middleName":"Patienter","secretWord":"$2a$12$FgH/NHryz3pkfyqtXdBS5.LJC/5kzrNmuKxtckFVxx8SrC/Xlx9MC"},{"id":2,"lastName":"Vortex","firstName":"Peregrinatione","middleName":"Omnes","secretWord":"$2a$12$SHggkyPjvKQXGZ.u82b56eVU.vXahGgX5/i2poZXAgvBB9SYjpZ62"}]
```

## 2. Получить информацию о клиенте по его идентификатору

### Пример запроса

`GET http://localhost:8080/client/1`

### Ответ

```json
{"id":1,"lastName":"Nunquam","firstName":"Ridetis","middleName":"Patienter","secretWord":"$2a$12$FgH/NHryz3pkfyqtXdBS5.LJC/5kzrNmuKxtckFVxx8SrC/Xlx9MC"}
```

## 3. Получить информацию о счетах клиента

### Пример запроса

`GET http://localhost:8080/account/1`

### Ответ

```json
[{"clientId":{"id":1,"lastName":"Nunquam","firstName":"Ridetis","middleName":"Patienter","secretWord":"$2a$12$FgH/NHryz3pkfyqtXdBS5.LJC/5kzrNmuKxtckFVxx8SrC/Xlx9MC"},"id":1,"accountNumber":123000,"balance":500,"accountType":"DEBIT","openingDate":"2020-06-19T17:00:00.000+00:00","expirationDate":"2030-06-19T17:00:00.000+00:00"},{"clientId":{"id":1,"lastName":"Nunquam","firstName":"Ridetis","middleName":"Patienter","secretWord":"$2a$12$FgH/NHryz3pkfyqtXdBS5.LJC/5kzrNmuKxtckFVxx8SrC/Xlx9MC"},"id":2,"accountNumber":123001,"balance":0,"accountType":"CREDIT","openingDate":"2018-05-21T17:00:00.000+00:00","expirationDate":"2028-05-21T17:00:00.000+00:00"}]
```

## 4. Получить информацию о транзакциях по счету клиента 

### Пример запроса

`GET http://localhost:8080/transaction/1`

### Ответ

```json
[{"accountId":{"clientId":{"id":1,"lastName":"Nunquam","firstName":"Ridetis","middleName":"Patienter","secretWord":"$2a$12$FgH/NHryz3pkfyqtXdBS5.LJC/5kzrNmuKxtckFVxx8SrC/Xlx9MC"},"id":1,"accountNumber":123000,"balance":500,"accountType":"DEBIT","openingDate":"2020-06-19T17:00:00.000+00:00","expirationDate":"2030-06-19T17:00:00.000+00:00"},"cashOrderId":{"accountId":{"clientId":{"id":1,"lastName":"Nunquam","firstName":"Ridetis","middleName":"Patienter","secretWord":"$2a$12$FgH/NHryz3pkfyqtXdBS5.LJC/5kzrNmuKxtckFVxx8SrC/Xlx9MC"},"id":1,"accountNumber":123000,"balance":500,"accountType":"DEBIT","openingDate":"2020-06-19T17:00:00.000+00:00","expirationDate":"2030-06-19T17:00:00.000+00:00"},"id":1,"orderType":"DEPOSIT","amount":500,"executionResult":"SUCCESS","creationDate":"2022-11-30T17:00:00.000+00:00"},"id":1,"creationDate":"2022-11-30T17:00:00.000+00:00","amount":500,"type":"DEPOSIT","senderAccountId":null,"executionResult":"SUCCESS"},{"accountId":{"clientId":{"id":1,"lastName":"Nunquam","firstName":"Ridetis","middleName":"Patienter","secretWord":"$2a$12$FgH/NHryz3pkfyqtXdBS5.LJC/5kzrNmuKxtckFVxx8SrC/Xlx9MC"},"id":1,"accountNumber":123000,"balance":500,"accountType":"DEBIT","openingDate":"2020-06-19T17:00:00.000+00:00","expirationDate":"2030-06-19T17:00:00.000+00:00"},"cashOrderId":{"accountId":{"clientId":{"id":1,"lastName":"Nunquam","firstName":"Ridetis","middleName":"Patienter","secretWord":"$2a$12$FgH/NHryz3pkfyqtXdBS5.LJC/5kzrNmuKxtckFVxx8SrC/Xlx9MC"},"id":1,"accountNumber":123000,"balance":500,"accountType":"DEBIT","openingDate":"2020-06-19T17:00:00.000+00:00","expirationDate":"2030-06-19T17:00:00.000+00:00"},"id":2,"orderType":"TRANSFER","amount":300,"executionResult":"SUCCESS","creationDate":"2022-11-30T17:00:00.000+00:00"},"id":3,"creationDate":"2022-12-01T17:00:00.000+00:00","amount":200,"type":"WITHDRAW","senderAccountId":null,"executionResult":"SUCCESS"}]
```

## 5. Получить информацию о кассовых ордерах по счету клиента

### Пример запроса

`GET http://localhost:8080/cashOrder/1`

### Ответ

```json
[{"accountId":{"clientId":{"id":1,"lastName":"Nunquam","firstName":"Ridetis","middleName":"Patienter","secretWord":"$2a$12$FgH/NHryz3pkfyqtXdBS5.LJC/5kzrNmuKxtckFVxx8SrC/Xlx9MC"},"id":1,"accountNumber":123000,"balance":500,"accountType":"DEBIT","openingDate":"2020-06-19T17:00:00.000+00:00","expirationDate":"2030-06-19T17:00:00.000+00:00"},"id":1,"orderType":"DEPOSIT","amount":500,"executionResult":"SUCCESS","creationDate":"2022-11-30T17:00:00.000+00:00"},{"accountId":{"clientId":{"id":1,"lastName":"Nunquam","firstName":"Ridetis","middleName":"Patienter","secretWord":"$2a$12$FgH/NHryz3pkfyqtXdBS5.LJC/5kzrNmuKxtckFVxx8SrC/Xlx9MC"},"id":1,"accountNumber":123000,"balance":500,"accountType":"DEBIT","openingDate":"2020-06-19T17:00:00.000+00:00","expirationDate":"2030-06-19T17:00:00.000+00:00"},"id":2,"orderType":"TRANSFER","amount":300,"executionResult":"SUCCESS","creationDate":"2022-11-30T17:00:00.000+00:00"},{"accountId":{"clientId":{"id":1,"lastName":"Nunquam","firstName":"Ridetis","middleName":"Patienter","secretWord":"$2a$12$FgH/NHryz3pkfyqtXdBS5.LJC/5kzrNmuKxtckFVxx8SrC/Xlx9MC"},"id":1,"accountNumber":123000,"balance":500,"accountType":"DEBIT","openingDate":"2020-06-19T17:00:00.000+00:00","expirationDate":"2030-06-19T17:00:00.000+00:00"},"id":3,"orderType":"WITHDRAW","amount":200,"executionResult":"SUCCESS","creationDate":"2022-12-01T17:00:00.000+00:00"}]
```

## 6. Создать транзакцию на перевод

### Пример запроса

`POST http://localhost:8080/transfer`
```json
{
  "receiverAccountId": 2,
  "senderAccountId": 1,
  "amount": 100,
  "secretWord": "ducunt"
}
```

## 7. Создать ордер на пополнение/снятие

### Примеры запроса

`POST http://localhost:8080/transfer`
```json
{
  "type": "DEPOSIT",
  "amount": 50,
  "accountId": 1,
  "secretWord": "ducunt"
}
```
```json
{
  "type": "WITHDRAW",
  "amount": 10,
  "accountId": 3,
  "secretWord": "guttuses"
}
```
