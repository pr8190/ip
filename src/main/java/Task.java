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
        String[] tr = temp.split(" ");
        if (tr.length <= 1)
            return null;
        if (tr[0].equals("todo"))
            return new Todo(temp.split("todo ")[1]);
        else if (tr[0].equals("deadline")) {
            String desc = temp.split("deadline ")[1].split(" /by ")[0];
            String dd = temp.split("deadline ")[1].split(" /by ")[1];
            return new Deadline(desc, dd);
        } else if (tr[0].equals("event")) {
            String desc = temp.split("event ")[1].split(" /from ")[0];
            String s = temp.split("event ")[1].split(" /from ")[1].split(" /to ")[0];
            String e = temp.split("event ")[1].split(" /from ")[1].split(" /to ")[1];
            return new Event(desc, s, e);
        }
        return null;
    }

    public String stringDescription() {
        return this.toString();
    }
}