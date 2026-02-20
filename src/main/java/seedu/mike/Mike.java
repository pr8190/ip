package seedu.mike;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The main class for the Mike chatbot application.
 * Mike is a task management chatbot that helps users track their todos,
 * deadlines, and events.
 */
public class Mike {

    /** The default file path for storing task data. */
    protected static String fileName = "./src/main/java/data/data.txt";

    /** The user interface component for interacting with the user. */
    protected Ui ui;

    /** The storage component for reading and writing task data. */
    protected Storage storage;

    /** The task list that manages all updations. */
    protected TaskList taskList;

    /**
     * Constructs a Mike chatbot instance with the specified data file.
     * Initializes the UI, storage, and task list components.
     *
     * @param fileName The path to the file where tasks are stored
     */
    public Mike(String fileName) {
        this.ui = new Ui();
        this.storage = new Storage(fileName);
        taskList = new TaskList(storage.readFromFile());
    }

    /**
     * Runs the main loop of the Mike chatbot.
     * Continuously reads user commands and executes them until the user enters
     * "bye".
     * Supported commands include: list, mark, unmark, delete, todo, deadline, and
     * event.
     */
    public void run() {
        ui.showWelcome();
        while (true) {
            String command = ui.readCommand();
            // ArrayList<Task> tasks = taskList.loadTasks();
            String[] commandSplit = command.split(" ");
            if (command.equals("bye")) { // When user inputs 'bye'
                storage.writeToFile(taskList.loadTasks());
                ui.showMessage("Bye. Hope to see you again soon!");
                ui.close();
                break;
            } else if (command.equals("list")) { // when user inputs 'list'
                ui.showMessage(taskList.list());
            } else if (commandSplit[0].equals("mark")) { // when user asks to mark a particular task as done
                ui.showMessage(taskList.mark(command));
            } else if (commandSplit[0].equals("unmark")) { // when user asks to unmark a particular task
                ui.showMessage(taskList.unmark(command));
            } else if (commandSplit[0].equals("delete")) { // when user asks to delete a particular task from the list
                ui.showMessage(taskList.delete(command));
            } else if (commandSplit[0].equals("find")) {
                ui.showMessage(taskList.find(command));
            } else if (commandSplit[0].equals("todo") || commandSplit[0].equals("deadline")
                    || commandSplit[0].equals("event")) {
                // classifying tasks as todo, deadline or event and handling errors
                if (commandSplit.length <= 1) {
                    ui.showMessage("OOPS!!! The description of a " + commandSplit[0]
                            + " cannot be empty.\n");
                    continue;
                }
                Task temTask = Task.classifyTask(command);
                if (temTask == null) {
                    continue;
                }
                taskList.add(temTask);
                ui.showMessage("Got it. I've added this task:\n" + temTask
                        + "\nNow you have " + taskList.size() + " tasks in the list.\n");
            } else {
                ui.showMessage("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
            }
        }
    }

    /**
     * The main entry point for the Mike chatbot application.
     * Creates a new Mike instance and runs it.
     *
     * @param args Command line arguments (not used)
     * @throws FileNotFoundException If the data file cannot be found
     * @throws IOException           If there is an error reading or writing to the
     *                               file
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        new Mike(fileName).run();
    }

    /**
     * To get the response when user inputs a string in the Mike application.
     *
     * @param String input provided by the user
     * @return String response for the inputted string
     */
    public String getResponse(String command) {
        String[] commandSplit = command.split(" ");
        if (command.equals("bye")) { // When user inputs 'bye'
            storage.writeToFile(taskList.loadTasks());
            ui.close();
            return "Bye. Hope to see you again soon!";
        } else if (command.equalsIgnoreCase("hi") || command.equalsIgnoreCase("hello")) {
            return "Hello! I am Mike - your personal chatbot for remembering tasks. These are the basic functionalities I can perform : "
                    + "\n1. Adding todo tasks\n2. Adding deadline tasks\n3. Adding event tasks\n4. Listing all the tasks"
                    + "\n5. Marking a tasks done\n6. Unmarking a task\n7. Deleting a task\n8. Finding tasks that matches the inputted keyword";
        } else if (command.equals("list")) { // when user inputs 'list'
            return taskList.list();
        } else if (commandSplit[0].equals("mark")) { // when user asks to mark a particular task as done
            return taskList.mark(command);
        } else if (commandSplit[0].equals("unmark")) { // when user asks to unmark a particular task
            return taskList.unmark(command);
        } else if (commandSplit[0].equals("delete")) { // when user asks to delete a particular task from the list
            return taskList.delete(command);
        } else if (commandSplit[0].equals("find")) {
            return taskList.find(command);
        } else if (commandSplit[0].equals("todo") || commandSplit[0].equals("deadline")
                || commandSplit[0].equals("event")) {
            // classifying tasks as todo, deadline or event and handling errors
            if (commandSplit.length <= 1) {
                return "OOPS!!! The description of a " + commandSplit[0]
                        + " cannot be empty.\n";
            }
            Task temTask = Task.classifyTask(command);
            if (temTask == null) {
                return "Oops!!! Looks like there is an error";
            }
            taskList.add(temTask);
            return "Got it. I've added this task:\n" + temTask
                    + "\nNow you have " + taskList.size() + " tasks in the list.\n";
        } else {
            return "OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        }
    }
}
