package by.epam.ayem.main;

/*Задание 2. Блокнот. Разработать консольное приложене, работающее с заметками в Блокноте.
Каждая заметка - это: Заметка (тема, дата создания, e-mail, сообщение).
    Общие пояснения к практическому заданию.
    1. В начале работы приложения данные должны считываться из файла, в конце работы - сохраняться в файл.
    2. У пользователя должна быть возможность найти запись по любому параметру или по группе параметров
    (группу параметров можно определить самостоятельно), получив требуемые записи в отсортированном виде, найти
    записи, текстовое поле которых содержит определенное слово, а так же добавить новую запись.
    3. Особое условие: поиск, сравнение и валидацию вводимой информации осуществлять с использованием регулярных
    выражений.
    4. Особое условие: проверку введенной информации на валидность должен осуществлять код, непосредственно
    добавляющий информацию.*/

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Note {

    private String topic;
    private String date;
    private final String DATE_PATTERN = "yyyy.MM.dd 'at' HH:mm:ss z";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
    private String email;
    private String message;
    private Calendar calendar = Calendar.getInstance();

    public Note(String topic, String email, String message) {
        this.topic = topic;
        this.date = addDate();
        this.email = email;
        this.message = message;
    }

    private String addDate() {
        return simpleDateFormat.format(calendar.getTime());
    }

    public Note(String topic, String email, String date, String message) {
        this.topic = topic;
        this.email = email;
        this.date = date;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Note: " + "\"" + topic + "\"" + ". Date: " + simpleDateFormat.format(calendar.getTime())
                + ". E-mail: " + email + ". \"" + message + "\"";
    }

    public String getTopic() {
        return topic;
    }

    public String getSimpleDateFormat() {
        return simpleDateFormat.format(calendar.getTime());
    }

    public String getEmail() {
        return email;
    }

    public String getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }
}
