package seedu.mike;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Mike {
    protected static String fileName = "./src/main/java/data.txt";
    protected Ui ui;
    protected Storage storage;
    protected TaskList taskList;

    public Mike(String fileName) {
        this.ui = new Ui();
        this.storage = new Storage(fileName);
        taskList = new TaskList(storage.readFromFile(), ui);
    }

    public void run() {
        ui.showWelcome();
        while (true) {
            String command = ui.readCommand();
            // ArrayList<Task> tasks = taskList.loadTasks();
            String[] commandSplit = command.split(" ");
            if (command.equals("bye")) { // When user inputs 'bye'
                storage.writeToFile(taskList.loadTasks());
                ui.showLine();
                ui.showMessage("Bye. Hope to see you again soon!");
                ui.showLine();
                ui.close();
                break;
            } else if (command.equals("list")) { // when user inputs 'list' and it lists out all the task in the
                                                 // arraylist
                taskList.list();
            } else if (commandSplit[0].equals("mark")) { // when user asks to mark a particular task as done
                taskList.mark(command);
            } else if (commandSplit[0].equals("unmark")) { // when user asks to unmark a particular task
                taskList.unmark(command);
            } else if (commandSplit[0].equals("delete")) { // when user asks to delete a particular task from the list
                taskList.delete(command);
            } else if (commandSplit[0].equals("todo") || commandSplit[0].equals("deadline")
                    || commandSplit[0].equals("event")) { // classifying tasks as todo, deadline or event and handling
                                                          // errors
                if (commandSplit.length <= 1) {
                    ui.showLine();
                    ui.showMessage("OOPS!!! The description of a " + commandSplit[0]
                            + " cannot be empty.\n");
                    ui.showLine();
                    continue;
                }
                Task temTask = Task.classifyTask(command);
                if (temTask == null)
                    continue;
                taskList.add(temTask);
                ui.showLine();
                ui.showMessage("Got it. I've added this task:\n" + //
                        temTask + "\nNow you have " + taskList.size() + " tasks in the list.\n");
                ui.showLine();
            } else {
                ui.showLine();
                ui.showMessage("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        new Mike(fileName).run();
    }
}
