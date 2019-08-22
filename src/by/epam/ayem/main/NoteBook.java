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

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class NoteBook {

    private List<Note> notes;
    private Writer writer = new Writer();
    private Reader reader = new Reader();
    private final String FILE_NAME = "NoteBook.txt";
    private Scanner scanner = new Scanner(System.in);

    public NoteBook() {
        this.notes = reader.readNoteFromFile(FILE_NAME);
    }

    void addNote(String topic, String email, String message) {
        Note note = new Note(topic, email, message);
        notes.add(note);
        System.out.println("New note were created.");
        writer.writeNoteToFile(note, FILE_NAME);
    }

    void deleteNote(int number) {
        File file = new File(FILE_NAME);
        file.delete();

        notes.remove(number);
        for (Note item : notes) {
            writer.writeNoteToFile(item, FILE_NAME);
        }
    }

    void findNoteByWord(List<Note> sortedNotes, String word) {
        boolean found = false;
        for (Note item : sortedNotes) {
            word = " " + word + " ";
            if (item.getMessage().contains(word)) {
                System.out.println(item.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No notes were found.");
        }
    }

    List<Note> findNote() {
        List<Note> sortedNotes = new ArrayList<>();

        while (!scanner.hasNextInt()) {
            scanner.next();
        }
        int filter = scanner.nextInt();
        scanner.nextLine();

        switch (filter) {
            case 1:
                System.out.println("Choose a topic: ");
                showTopics();
                System.out.println("\n0. Menu.");
                while (!scanner.hasNextInt()) {
                    scanner.next();
                }
                int number = scanner.nextInt();
                if (number != 0) {
                    String topic = getTopicByNumber(number);
                    sortedNotes = findNote(topic);
                }
                break;
            case 2:
                System.out.println("Enter a year: ");
                String year = scanner.nextLine();
                System.out.println("Enter a month: ");
                String month = scanner.nextLine();
                System.out.println("Enter a day: ");
                String day = scanner.nextLine();
                sortedNotes = findNote(year, month, day);
                break;
            case 3:
                System.out.println("Enter a topic: ");
                String topic = scanner.nextLine();
                System.out.println("Enter a year: ");
                year = scanner.nextLine();
                System.out.println("Enter a month: ");
                month = scanner.nextLine();
                System.out.println("Enter a day: ");
                day = scanner.nextLine();
                sortedNotes = findNote(topic, year, month, day);
                break;
            case 4:
                System.out.println("Enter the e-mail: ");
                String email = scanner.nextLine();
                sortedNotes = findNoteByEmail(email);
                break;
            case 0:
                break;
        }
        return sortedNotes;
    }

    private List<Note> findNote(String topic) {
        List<Note> sortedNotes = new ArrayList<>();

        for (Note item : notes) {
            if (item.getTopic().equalsIgnoreCase(topic)) {
                System.out.println(notes.indexOf(item) + ". " + item.toString());
                sortedNotes.add(item);
            }
        }
        return sortedNotes;
    }

    private List<Note> findNoteByEmail(String email) {
        List<Note> sortedNotes = new ArrayList<>();

        for (Note item : notes) {
            if (item.getEmail().equalsIgnoreCase(email)) {
                System.out.println(notes.indexOf(item) + ". " + item.toString());
                sortedNotes.add(item);
            }
        }
        return sortedNotes;
    }

    private List<Note> findNote(String year, String month, String day) {
        List<Note> sortedNotes = new ArrayList<>();

        String date = year + "." + month + "." + day;
        for (Note item : notes) {
            if (item.getDate().contains(date)) {
                System.out.println(notes.indexOf(item) + ". " + item.toString());
                sortedNotes.add(item);
            }
        }
        return sortedNotes;
    }

    private List<Note> findNote(String topic, String year, String month, String day) {
        List<Note> sortedNotes = new ArrayList<>();

        String date = year + "." + month + "." + day;
        for (Note item : notes) {
            if (item.getTopic().equalsIgnoreCase(topic) && item.getDate().contains(date)) {
                System.out.println(notes.indexOf(item) + ". " + item.toString());
                sortedNotes.add(item);
            }
        }
        return sortedNotes;
    }

    void showNotes() {
        boolean hasNotes = false;

        for (Note note : notes) {
            System.out.println((notes.indexOf(note) + 1) + ". " + note.toString());
            hasNotes = true;
        }
        if(!hasNotes){
            System.out.println("There are no notes in the Notebook.");
        }
    }

    void showTopics() {
        List<String> topics = new ArrayList<>();
        int number = 0;

        for (Note note : notes) {
            if (!topics.contains(note.getTopic())) {
                topics.add(note.getTopic());
            }
        }

        List<String> subList = topics.subList(0, topics.size());
        Collections.sort(subList);

        for (String topic : subList) {
            number++;
            System.out.println(number + ". " + topic);
        }
    }

    String getTopicByNumber(int number) {
        List<String> topics = new ArrayList<>();
        String topic = "";

        for (Note note : notes) {
            if (!topics.contains(note.getTopic())) {
                topics.add(note.getTopic());
            }
        }

        List<String> subList = topics.subList(0, topics.size());
        Collections.sort(subList);

        for (String item : subList) {
            if (subList.indexOf(item) == (number - 1)) {
                topic = item;
            }
        }
        return topic;
    }

    List<Note> getNotes() {
        return notes;
    }
}
