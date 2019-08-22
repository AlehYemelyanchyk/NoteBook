package by.epam.ayem.main;

import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    void writeNoteToFile(Note note, String fileName) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(note.getTopic() + "-;-"
                    + note.getEmail() + "-;-"
                    + note.getSimpleDateFormat() + "-;-"
                    + note.getMessage() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
