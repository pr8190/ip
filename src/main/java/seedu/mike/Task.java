package seedu.mike;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a description and completion status.
 * This is the base class for different types of tasks (Todo, Deadline, Event).
 * Tasks can be marked as done or undone, and can be converted to/from string
 * format for storage.
 */
public class Task {
    /** The description of the task. */
    protected String description;

    /** The completion status of the task (true if done, false otherwise). */
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * The task is initially marked as not done.
     * 
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon representing whether the task is done.
     * 
     * @return "X" if the task is done, " " (space) if not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Marks the task as done.
     * 
     * @return The string representation of the task after marking it as done
     */
    public String markAsDone() {
        isDone = true;
        return this.toString();
    }

    /**
     * Marks the task as undone.
     * 
     * @return The string representation of the task after marking it as undone
     */
    public String markAsUndone() {
        isDone = false;
        return this.toString();
    }

    /**
     * Creates a Task object from a command string.
     * Parses the command to determine the task type (todo, deadline, or event)
     * and creates the appropriate Task subclass.
     * 
     * Expected formats:
     * - Todo: "todo DESCRIPTION"
     * - Deadline: "deadline DESCRIPTION /by yyyy-MM-dd"
     * - Event: "event DESCRIPTION /from yyyy-MM-dd HH:mm /to HH:mm"
     * 
     * @param temp The command string to parse
     * @return A Task object (Todo, Deadline, or Event), or null if parsing fails
     */
    public static Task classifyTask(String temp) {
        try {
            String[] tr = temp.split(" ");
            if (tr[0].equals("todo"))
                return new Todo(temp.split("todo ")[1]);
            else if (tr[0].equals("deadline")) {
                String desc = temp.split("deadline ")[1].split(" /by ")[0];
                LocalDate dd = LocalDate.parse(temp.split("deadline ")[1].split(" /by ")[1],
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return new Deadline(desc, dd);
            } else if (tr[0].equals("event")) {
                String desc = temp.split("event ")[1].split(" /from ")[0];
                LocalDateTime s = LocalDateTime.parse(temp.split("event ")[1].split(" /from ")[1].split(" /to ")[0],
                        DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm"));
                LocalTime e = LocalTime.parse(temp.split("event ")[1].split(" /from ")[1].split(" /to ")[1]);
                return new Event(desc, s, e);
            }
            System.out.println(
                    "-------------------------------------\nOOPS!!! There is an error in the format.(\n-------------------------------------\n");
            return null;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(
                    "-------------------------------------\nOOPS!!! Looks like there is an error in the format.\n-------------------------------------\n");
            return null;
        } catch (DateTimeParseException e) {
            System.out.println(
                    "-------------------------------------\nOOPS!!! Looks like there is an error in the format." +
                            "\nFor Date : yyyy-MM-dd\nFor Time : HH:mm\nFor DateTime : yyyy-MM-dd HH:mm" +
                            "\n-------------------------------------\n");
            return null;
        }
    }

    /**
     * Creates a Task object from its stored string format.
     * The stored format includes the completion status followed by the task
     * details.
     * This method is used when reading tasks from the storage file.
     * 
     * @param temp The stored string representation of the task (e.g., "X todo read
     *             book")
     * @return A Task object with the appropriate completion status
     */
    public static Task taskFactory(String temp) {
        String[] split = temp.split(" ", 2);
        Task task = Task.classifyTask(split[1]);
        if (split[0].equals("X"))
            task.markAsDone();
        return task;
    }

    /**
     * Returns the description of the task.
     * This method is used when saving tasks to the storage file.
     * 
     * @return The task description
     */
    public String stringDescription() {
        return this.description;
    }
}