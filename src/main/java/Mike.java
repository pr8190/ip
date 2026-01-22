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
                str.get(k - 1).isDone = true;
                System.out.println("-------------------------------------\nNice! I've marked this task as done: \n" + //
                        str.get(k - 1) + //
                        "\n-------------------------------------\n" + //
                        "");
            } else if (tr[0].equals("unmark")) {
                int k = Integer.parseInt(tr[1]);
                str.get(k - 1).isDone = false;
                System.out.println(
                        "-------------------------------------\nOK, I've marked this task as not done yet: \n" + //
                                str.get(k - 1) + //
                                "\n-------------------------------------\n" + //
                                "");
            } else {
                System.out.println("-------------------------------------\nadded: " + //
                        temp + //
                        "\n-------------------------------------\n" + //
                        "");
                str.add(new Task(temp));
            }
        }
    }
}
