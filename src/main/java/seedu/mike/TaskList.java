package seedu.mike;

import java.util.ArrayList;

/**
 * Manages a list of tasks for the Mike chatbot.
 * This class handles operations such as adding, deleting, marking, and listing
 * tasks.
 */
public class TaskList {
    /** The minimum number of parts expected when splitting a command string. */
    private static final int MIN_SPLITS = 2;

    /** The list of tasks managed by this TaskList. */
    protected ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks The initial list of tasks to be managed
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the ArrayList of tasks currently in the task list.
     *
     * @return The ArrayList containing all tasks
     */
    public ArrayList<Task> loadTasks() {
        return this.tasks;
    }

    /**
     * Returns a formatted string representation of all tasks in the list.
     * Each task is numbered and displayed on a separate line.
     *
     * @return A string containing all tasks with their numbers, or a header message
     *         if the list is displayed
     */
    public String list() {
        StringBuilder stringBuilder = new StringBuilder().append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append((i + 1) + ". " + tasks.get(i) + "\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Marks a task as done based on the given command string.
     * The command should be in the format "mark INDEX" where INDEX is a 1-based
     * task number.
     *
     * @param mark The mark command string (e.g., "mark 2")
     * @return A message indicating success or describing the error that occurred
     */
    public String mark(String mark) {
        if (mark.split(" ").length != MIN_SPLITS) {
            return "Oops!!! The task to be marked is missing.\n";
        }
        try {
            int index = Integer.parseInt(mark.split(" ")[1]);
            if (index > tasks.size()) {
                return "Oops!!! Index out of Range.\n";
            } else {
                return "Nice! I've marked this task as done: \n"
                        + tasks.get(index - 1).markAsDone() + "\n";
            }
        } catch (NumberFormatException e) {
            return "Oops!!! Not a valid number.\n";
        }
    }

    /**
     * Marks a task as not done based on the given command string.
     * The command should be in the format "unmark INDEX" where INDEX is a 1-based
     * task number.
     *
     * @param unmark The unmark command string (e.g., "unmark 2")
     * @return A message indicating success or describing the error that occurred
     */
    public String unmark(String unmark) {
        if (unmark.split(" ").length != MIN_SPLITS) {
            return "Oops!!! The task to be unmarked is missing.\n";
        }
        try {
            int index = Integer.parseInt(unmark.split(" ")[1]);
            if (index > tasks.size()) {
                return "Oops!!! Index out of Range.\n";
            } else {
                return "Ok! I've unmarked this task: \n"
                        + tasks.get(index - 1).markAsUndone() + "\n";
            }
        } catch (NumberFormatException e) {
            return "Oops!!! Not a valid number.\n";
        }
    }

    /**
     * Deletes a task from the list based on the given command string.
     * The command should be in the format "delete INDEX" where INDEX is a 1-based
     * task number.
     *
     * @param delete The delete command string (e.g., "delete 2")
     * @return A message indicating the deleted task and the new list size, or an
     *         error message
     */
    public String delete(String delete) {
        if (delete.split(" ").length != MIN_SPLITS) {
            return "Oops!!! The task to be deleted is missing.\n";
        }
        try {
            int index = Integer.parseInt(delete.split(" ")[1]);
            if (index > tasks.size()) {
                return "Oops!!! Index out of Range.\n";
            } else {
                Task rem = tasks.remove(index - 1);
                return "Noted. I've removed this task:\n"
                        + rem + "\nNow you have " + tasks.size() + " tasks in the list.\n";
            }
        } catch (NumberFormatException e) {
            return "Oops!!! Not a valid number.\n";
        }
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The task to be added to the list
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns the number of tasks currently in the list.
     *
     * @return The size of the task list
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns the tasks that contains the keyword inputted by the user.
     *
     * @return The tasks that has the given keyword from the user
     */
    public String find(String find) {
        if (find.split(" ").length != MIN_SPLITS) {
            return "Oops!!! The keyword for the tasks to be found is missing.\n";
        }
        try {
            StringBuilder stringBuilder = new StringBuilder().append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                String[] parsedDescription = tasks.get(i).getDescription().split(" ");
                for (String string : parsedDescription) {
                    if (string.equals(find.split(" ")[1])) {
                        stringBuilder.append((i + 1) + ". " + tasks.get(i) + "\n");
                    }
                }
            }
            return stringBuilder.toString();
        } catch (ArrayIndexOutOfBoundsException exception) {
            return "Oops!!!There is an error in the format\n";
        }

    }

    /**
     * Provides a list of upcoming deadlines and events that are due or happening
     * within the next 3 days.
     * This method checks all tasks in the task list and identifies those that are
     * either deadlines
     * or events occurring within the next 3 days.
     *
     * @return A string containing the list of upcoming deadlines and events within
     *         3 days
     */
    public String reminders() {
        int count = 0;
        StringBuilder stringBuilder = new StringBuilder().append("Upcoming deadlines (within 3 days):\n");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof Deadline) {
                Deadline deadline = (Deadline) tasks.get(i);
                if (deadline.isDueSoon()) {
                    stringBuilder.append((count + 1) + ". " + tasks.get(i) + "\n");
                    count++;
                }
            } else if (tasks.get(i) instanceof Event) {
                Event event = (Event) tasks.get(i);
                if (event.isHappeningSoon()) {
                    stringBuilder.append((count + 1) + ". " + tasks.get(i) + "\n");
                    count++;
                }
            }
        }
        return stringBuilder.toString();
    }
}
