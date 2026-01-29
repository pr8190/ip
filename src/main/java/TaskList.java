import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;
    protected Ui ui;

    public TaskList(ArrayList<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public ArrayList<Task> loadTasks() {
        return this.tasks;
    }

    public void list() { // updating the user on the number of tasks left
        System.out.println("-------------------------------------\nHere are the tasks in your list: \n");
        for (int i = 0; i < tasks.size(); i++)
            System.out.println((i + 1) + ". " + tasks.get(i) + "\n");
        ui.showLine();
    }

    public void mark(String mark) { // updating the list to mark a task done
        if (mark.split(" ").length != 2) {
            ui.showLine();
            ui.showError("Oops!!! The task to be marked is missing.\n");
            ui.showLine();
            return;
        }
        try {
            int index = Integer.parseInt(mark.split(" ")[1]);
            if (index > tasks.size()) {
                ui.showLine();
                ui.showError("Oops!!! Index out of Range.\n");
                ui.showLine();
            } else {
                ui.showLine();
                ui.showMessage("Nice! I've marked this task as done: \n" + //
                        tasks.get(index - 1).markAsDone() + "\n");
                ui.showLine();
            }
        } catch (NumberFormatException e) {
            ui.showLine();
            ui.showError("Oops!!! Not a valid number.\n");
            ui.showLine();
        }
    }

    public void unmark(String unmark) { // updating the list to unmark a task
        if (unmark.split(" ").length != 2) {
            ui.showLine();
            ui.showError("Oops!!! The task to be unmarked is missing.\n");
            ui.showLine();
            return;
        }
        try {
            int index = Integer.parseInt(unmark.split(" ")[1]);
            if (index > tasks.size()) {
                ui.showLine();
                ui.showError("Oops!!! Index out of Range.\n");
                ui.showLine();
            } else {
                ui.showLine();
                ui.showMessage("Ok! I've unmarked this task: \n" + //
                        tasks.get(index - 1).markAsUndone() + "\n");
                ui.showLine();
            }
        } catch (NumberFormatException e) {
            ui.showLine();
            ui.showError("Oops!!! Not a valid number.\n");
            ui.showLine();
        }
    }

    public void delete(String delete) { // updating the list to delete a task
        if (delete.split(" ").length != 2) {
            ui.showLine();
            ui.showError("Oops!!! The task to be deleted is missing.\n");
            ui.showLine();
            return;
        }
        try {
            int index = Integer.parseInt(delete.split(" ")[1]);
            if (index > tasks.size()) {
                ui.showLine();
                ui.showError("Oops!!! Index out of Range.\n");
                ui.showLine();
            } else {
                Task rem = tasks.remove(index - 1);
                ui.showLine();
                ui.showMessage("Noted. I've removed this task:\n" + //
                        rem + "\nNow you have " + tasks.size() + " tasks in the list.\n");
                ui.showLine();
            }
        } catch (NumberFormatException e) {
            ui.showLine();
            ui.showError("Oops!!! Not a valid number.\n");
            ui.showLine();
        }
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public int size() {
        return this.tasks.size();
    }
}
