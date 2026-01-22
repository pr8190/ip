import java.util.ArrayList;
import java.util.Scanner;

public class Mike {
    public static void main(String[] args) {
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
            if (temp.equals("bye")) {
                System.out.println(
                        "-------------------------------------\nBye. Hope to see you again soon!\n-------------------------------------\n");
                break;
            } else if (temp.equals("list")) {
                System.out.println("-------------------------------------\nHere are the tasks in your list: \n");
                for (int i = 0; i < str.size(); i++)
                    System.out.println((i + 1) + ". " + str.get(i) + "\n");
                System.out.println("-------------------------------------\n");
            } else if (tr[0].equals("mark")) {
                int k = Integer.parseInt(tr[1]);
                System.out.println("-------------------------------------\nNice! I've marked this task as done: \n" + //
                        str.get(k - 1).markAsDone() + //
                        "\n-------------------------------------\n" + //
                        "");
            } else if (tr[0].equals("unmark")) {
                int k = Integer.parseInt(tr[1]);
                System.out.println(
                        "-------------------------------------\nOK, I've marked this task as not done yet: \n" + //
                                str.get(k - 1).markAsUndone() + //
                                "\n-------------------------------------\n" + //
                                "");
            } else {
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
