# Efko Test Service
Сервис хранения заметок. 
В качестве хранилища приложение использует MySQL. Схема базы находится в файле *schema.sql*. Перед запуском необходимо сконфигурировать MySQL и установить данные для подключения: 

parameter  | value
-----------| -----
port       | 3306
host | localhost
username   | root
password   | 12345
database   | notes_db

Для запуска выполните в директории с проектом:
```
gradle appStartWar
```
### Использование
#### Создать заметку
```
>>> POST /notes?category=[category_name]&text=[note_text]
>>> Authorization: [user_token]
<<< 201 Created
<<< Location: /notes/[noteId]
```
[user_token] - токен для доступа к заметке. Только пользователи с указанным токеном смогут её получить.
#### Получить заметку по ID
```
>>> GET /notes/[noteId]
>>> Authorization: [user_token]
<<< 200 OK
<<< Content-Type: application/json
```
#### Получить все доступные заметки
```
>>> GET /notes
>>> Authorization: [user_token]
<<< 200 OK
<<< Content-Type: application/json
```
