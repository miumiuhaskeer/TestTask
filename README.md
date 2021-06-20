# Тестовое задание
Позволяет выводить на экран таблицу, а также редактировать ее. Ниже продемонстрированно изображение самой таблицы и управляющих кнопок:
</br></br>
<img src="https://github.com/miumiuhaskeer/TestTask/blob/master/screenshots/Screenshot_1.png" width="503.2" height="324" />
</br></br>

Для того, чтобы создать кота, нажмите на кнопку "Create", затем введите данные кота (возраст и кличку):
</br></br>
<img src="https://github.com/miumiuhaskeer/TestTask/blob/master/screenshots/Screenshot_2.png" width="390.4" height="208.8" />
</br></br>

Для редактирования кота (вдруг постарел), нажмите на строку с котом, затем на кнопку "Edit":
</br></br>
<img src="https://github.com/miumiuhaskeer/TestTask/blob/master/screenshots/Screenshot_4.png" width="410.4" height="223.2" />
</br></br>

Чтобы удалить кота, нужно нажать на строку с котом, который мы хотим удалить, затем на "Delete":
</br></br>
<img src="https://github.com/miumiuhaskeer/TestTask/blob/master/screenshots/Screenshot_3.png" width="352" height="188" />
</br></br>

Для того, чтобы инициализировать таблицу, воспользуемся SQL скриптом:
```sql
CREATE TABLE cats (
    id int not null,
    name varchar,
    age int,
    PRIMARY KEY (id)
);
CREATE SEQUENCE cat_seq INCREMENT 1 START 1 MINVALUE 1;
```
