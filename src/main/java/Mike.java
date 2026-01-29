import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Mike {
    protected static String fileName = "./src/main/java/data.txt";

    public static ArrayList<Task> readFromFile() throws FileNotFoundException {
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

    public static void writeToFile(ArrayList<Task> tasks) throws IOException {
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

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner sc = new Scanner(System.in);
        String logo = " ___  ___ __ __            \n"
                + "|   \\/   |__|  | __ ____ \n"
                + "|  |\\/|  |__|  |/  / __ \\ \n"
                + "|  |  |  |  |     < ____/\n"
                + "|__|  |__|__|__|\\__\\____|\n";
        System.out
                .println("Hello! I'm\n" + logo
                        + "\nWhat can I do for you?\n-------------------------------------\n");
        ArrayList<Task> tasks = readFromFile();
        while (true) {
            String temp = sc.nextLine();
            String[] tr = temp.split(" ");
            if (temp.equals("bye")) { // When user inputs 'bye'
                writeToFile(tasks);
                sc.close();
                System.out.println(
                        "-------------------------------------\nBye. Hope to see you again soon!\n-------------------------------------\n");
                sc.close();
                break;
            } else if (temp.equals("list")) { // when user inputs 'list' and it lists out all the task in the arraylist
                Update.list(tasks);
            } else if (tr[0].equals("mark")) { // when user asks to mark a particular task as done
                Update.mark(tasks, temp);
            } else if (tr[0].equals("unmark")) { // when user asks to unmark a particular task
                Update.unmark(tasks, temp);
            } else if (tr[0].equals("delete")) { // when user asks to delete a particular task from the list
                Update.delete(tasks, temp);
            } else { // classifying tasks as todo, deadline or event and handling errors
                if (tr.length <= 1 && (tr[0].equals("todo") || tr[0].equals("deadline") || tr[0].equals("event"))) {
                    System.out.println("-------------------------------------\nOOPS!!! The description of a " + tr[0]
                            + " cannot be empty.\n-------------------------------------\n");
                    continue;
                } else if (tr.length <= 1) {
                    System.out.println(
                            "-------------------------------------\nOOPS!!! I'm sorry, but I don't know what that means :-(\n-------------------------------------\n");
                    continue;
                }
                Task temTask = Task.classifyTask(temp);
                if (temTask == null)
                    continue;
                tasks.add(temTask);
                System.out.println("-------------------------------------\nGot it. I've added this task:\n" + //
                        temTask + //
                        "\nNow you have " + tasks.size()
                        + " tasks in the list.\n-------------------------------------\n"
                        + //
                        "");
            }
        }
    }
}
