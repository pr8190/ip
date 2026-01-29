import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String markAsDone() {
        isDone = true;
        return this.toString();
    }

    public String markAsUndone() {
        isDone = false;
        return this.toString();
    }

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

    public static Task taskFactory(String temp) {
        String[] split = temp.split(" ", 2);
        Task task = Task.classifyTask(split[1]);
        if (split[0].equals("X"))
            task.markAsDone();
        return task;
    }

    public String stringDescription() {
        return this.description;
    }
}