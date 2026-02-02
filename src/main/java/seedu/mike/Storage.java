package seedu.mike;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the reading and writing of task data to a file.
 * This class manages persistent storage of tasks, allowing tasks to be saved
 * and loaded between program sessions.
 */
public class Storage {

    /** The path to the file where tasks are stored. */
    protected String fileName;

    /**
     * Constructs a Storage object with the specified file path.
     * 
     * @param fileName The path to the file where tasks will be stored
     */
    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Reads tasks from the storage file and returns them as an ArrayList.
     * If the file is not found, an empty ArrayList is returned and an error message
     * is printed.
     * Each line in the file is converted into a Task object using the Task factory
     * method.
     * 
     * @return An ArrayList containing all tasks read from the file, or an empty
     *         list if file not found
     */
    public ArrayList<Task> readFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File f = new File(fileName);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                Task temTask = Task.taskFactory(sc.nextLine());
                tasks.add(temTask);
            }
            sc.close();
            return tasks;
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            return tasks;
        }

    }

    /**
     * Writes the given list of tasks to the storage file.
     * Each task is written on a new line using its string representation.
     * If an IOException occurs, an error message is printed.
     * 
     * @param tasks The ArrayList of tasks to be written to the file
     */
    public void writeToFile(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(fileName);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                if (i == 0)
                    sb.append(tasks.get(i).stringDescription());
                else
                    sb.append(System.lineSeparator() + tasks.get(i).stringDescription());
            }
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("File Not Found");
        }

    }
}
