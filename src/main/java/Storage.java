import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
    }

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
