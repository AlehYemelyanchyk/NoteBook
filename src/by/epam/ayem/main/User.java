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

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {

    private Scanner scanner = new Scanner(System.in);
    List<Note> notes = new ArrayList<>();

    public void run() {
        NoteBook noteBook = new NoteBook();

        boolean quit = false;

        while (!quit) {

            System.out.println("\nMake a choice:\n" +
                    "1. Add a new note.\n" +
                    "2. Show all notes.\n" +
                    "3. Find a note.\n" +
                    "\n0. Quit");

            while (!scanner.hasNextInt()) {
                scanner.next();
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter topic:");
                    String topic = scanner.nextLine();
                    System.out.println("Enter e-mail:");
                    String email = scanner.nextLine();
                    System.out.println("Enter message:");
                    String message = scanner.nextLine();

                    noteBook.addNote(topic, email, message);
                    break;
                case 2:
                    noteBook.showNotes();
                    break;
                case 3:
                    System.out.println("Filter by:\n" +
                            "1. Topic.\n" +
                            "2. Date.\n" +
                            "3. Topic and date.\n" +
                            "4. E-mail.\n" +
                            "\n0. Menu");

                    notes = noteBook.findNote();

                    if (notes.size() == 0) {
                        System.out.println("No notes were found with such filter.");
                    } else {
                        System.out.println("\n1. Delete note.\n" +
                                "2. Find by word.\n" +
                                "\n0. Menu.");

                        boolean isRightNumber = false;
                        int number = 0;

                        while (!isRightNumber) {
                            while (!scanner.hasNextInt()) {
                                scanner.next();
                            }
                            number = scanner.nextInt();
                            scanner.nextLine();
                            if (number >= 0 && number <= 2) {
                                isRightNumber = true;
                            }
                        }

                        if (number == 1) {
                            System.out.println("Enter a number of the note:");
                            while (!scanner.hasNextInt()) {
                                scanner.next();
                            }
                            int numberDel = scanner.nextInt();
                            scanner.nextLine();
                            noteBook.deleteNote(numberDel);
                        } else if (number == 2) {
                            System.out.println("Enter a word to search:");
                            String word = scanner.nextLine();
                            noteBook.findNoteByWord(notes, word);
                        } else {
                            break;
                        }
                    }
                    break;
                case 0:
                    quit = true;
                    break;
            }
        }
    }
}
