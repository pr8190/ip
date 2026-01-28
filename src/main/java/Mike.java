import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
//import java.io.FileWriter;
import java.io.FileNotFoundException;

public class Mike {

    public static ArrayList<Task> readFromFile(String filename) throws FileNotFoundException {
        ArrayList<Task> tem = new ArrayList<>();
        try {
            File f = new File(filename);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                Task temTask = Task.classifyTask(sc.nextLine());
                System.out.println(temTask);
            }
            return tem;
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFound");
            return null;
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        readFromFile("./src/main/java/data.txt");
        Scanner sc = new Scanner(System.in);
        String logo = " ___  ___ __ __            \n"
                + "|   \\/   |__|  | __ ____ \n"
                + "|  |\\/|  |__|  |/  / __ \\ \n"
                + "|  |  |  |  |     < ____/\n"
                + "|__|  |__|__|__|\\__\\____|\n";
        System.out
                .println("Hello! I'm\n" + logo
                        + "\nWhat can I do for you?\n-------------------------------------\n");
        ArrayList<Task> str = new ArrayList<Task>();
        while (true) {
            String temp = sc.nextLine();
            String[] tr = temp.split(" ");
            if (temp.equals("bye")) { // When user inputs 'bye'
                System.out.println(
                        "-------------------------------------\nBye. Hope to see you again soon!\n-------------------------------------\n");
                break;
            } else if (temp.equals("list")) { // when user inputs 'list' and it lists out all the task in the arraylist
                System.out.println("-------------------------------------\nHere are the tasks in your list: \n");
                for (int i = 0; i < str.size(); i++)
                    System.out.println((i + 1) + ". " + str.get(i) + "\n");
                System.out.println("-------------------------------------\n");
            } else if (tr[0].equals("mark")) { // when user asks to mark a particular task as done
                if (tr.length <= 1) {
                    System.out.println("-------------------------------------\n" + //
                            "Oops!!! The task to be marked is missing.\n-------------------------------------\n" + //
                            "");
                    continue;
                }
                int k = Integer.parseInt(tr[1]);
                System.out.println("-------------------------------------\nNice! I've marked this task as done: \n" + //
                        str.get(k - 1).markAsDone() + //
                        "\n-------------------------------------\n" + //
                        "");
            } else if (tr[0].equals("unmark")) { // when user asks to unmark a particular task
                if (tr.length <= 1) {
                    System.out.println("-------------------------------------\n" + //
                            "Oops!!! The task to be unmarked is missing.\n-------------------------------------\n" + //
                            "");
                    continue;
                }
                int k = Integer.parseInt(tr[1]);
                System.out.println(
                        "-------------------------------------\nOK, I've marked this task as not done yet: \n" + //
                                str.get(k - 1).markAsUndone() + //
                                "\n-------------------------------------\n" + //
                                "");
            } else if (tr[0].equals("delete")) { // when user asks to delete a particular task from the list
                if (tr.length <= 1) {
                    System.out.println("-------------------------------------\n" + //
                            "Oops!!! The task to be deleted is missing.\n-------------------------------------\n" + //
                            "");
                    continue;
                }
                int k = Integer.parseInt(tr[1]);
                Task rem = str.remove(k - 1);
                System.out.println(
                        "-------------------------------------\nNoted. I've removed this task:\n" + //
                                rem + //
                                "\nNow you have " + str.size()
                                + " tasks in the list.\n-------------------------------------\n" + //
                                "");
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
                str.add(temTask);
                System.out.println("-------------------------------------\nGot it. I've added this task:\n" + //
                        temTask + //
                        "\nNow you have " + str.size() + " tasks in the list.\n-------------------------------------\n"
                        + //
                        "");
            }
        }
    }
}
