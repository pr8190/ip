import java.util.ArrayList;

public class Update {
    public static void list(ArrayList<Task> tasks) { // updating the user on the number of tasks left
        System.out.println("-------------------------------------\nHere are the tasks in your list: \n");
        for (int i = 0; i < tasks.size(); i++)
            System.out.println((i + 1) + ". " + tasks.get(i) + "\n");
        System.out.println("-------------------------------------\n");
    }

    public static void mark(ArrayList<Task> tasks, String mark) { // updating the list to mark a task done
        if (mark.split(" ").length != 2) {
            System.out.println("-------------------------------------\n" + //
                    "Oops!!! The task to be marked is missing.\n-------------------------------------\n" + //
                    "");
            return;
        }
        try {
            int index = Integer.parseInt(mark.split(" ")[1]);
            if (index > tasks.size()) {
                System.out.println("-------------------------------------\n" + //
                        "Oops!!! Index out of Range.\n-------------------------------------\n" + //
                        "");
            } else {
                System.out.println("-------------------------------------\nNice! I've marked this task as done: \n" + //
                        tasks.get(index - 1).markAsDone() + //
                        "\n-------------------------------------\n" + //
                        "");
            }
        } catch (NumberFormatException e) {
            System.out.println("-------------------------------------\n" + //
                    "Oops!!! Not a valid number.\n-------------------------------------\n" + //
                    "");
        }
    }

    public static void unmark(ArrayList<Task> tasks, String unmark) { // updating the list to unmark a task
        if (unmark.split(" ").length != 2) {
            System.out.println("-------------------------------------\n" + //
                    "Oops!!! The task to be unmarked is missing.\n-------------------------------------\n" + //
                    "");
            return;
        }
        try {
            int index = Integer.parseInt(unmark.split(" ")[1]);
            if (index > tasks.size()) {
                System.out.println("-------------------------------------\n" + //
                        "Oops!!! Index out of Range.\n-------------------------------------\n" + //
                        "");
            } else {
                System.out.println("-------------------------------------\nOk! I've unmarked this task: \n" + //
                        tasks.get(index - 1).markAsUndone() + //
                        "\n-------------------------------------\n" + //
                        "");
            }
        } catch (NumberFormatException e) {
            System.out.println("-------------------------------------\n" + //
                    "Oops!!! Not a valid number.\n-------------------------------------\n" + //
                    "");
        }
    }

    public static void delete(ArrayList<Task> tasks, String delete) { // updating the list to delete a task
        if (delete.split(" ").length != 2) {
            System.out.println("-------------------------------------\n" + //
                    "Oops!!! The task to be deleted is missing.\n-------------------------------------\n" + //
                    "");
            return;
        }
        try {
            int index = Integer.parseInt(delete.split(" ")[1]);
            if (index > tasks.size()) {
                System.out.println("-------------------------------------\n" + //
                        "Oops!!! Index out of Range.\n-------------------------------------\n" + //
                        "");
            } else {
                Task rem = tasks.remove(index - 1);
                System.out.println(
                        "-------------------------------------\nNoted. I've removed this task:\n" + //
                                rem + //
                                "\nNow you have " + tasks.size()
                                + " tasks in the list.\n-------------------------------------\n" + //
                                "");
            }
        } catch (NumberFormatException e) {
            System.out.println("-------------------------------------\n" + //
                    "Oops!!! Not a valid number.\n-------------------------------------\n" + //
                    "");
        }
    }
}
