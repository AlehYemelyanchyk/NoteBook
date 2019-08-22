package by.epam.ayem.main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    private List<Note> notes = new ArrayList<>();

    List<Note> readNoteFromFile(String fileName) {
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            String c;
            String[] subStr;

            while ((c = bufferedReader.readLine()) != null) {
                subStr = c.split("-;-");
                if (subStr.length > 3) {
                    notes.add(new Note(subStr[0], subStr[1], subStr[2], subStr[3]));
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            File file = new File(fileName);
            file.createNewFile();
        } finally {
            return notes;
        }
    }
}
