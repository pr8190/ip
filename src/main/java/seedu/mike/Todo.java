package seedu.mike;

/**
 * represents a task that is to be completed with no specific completion date or
 * time
 */
public class Todo extends Task {

    /**
     * creates a todo task
     *
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String stringDescription() {
        String status = super.getStatusIcon();
        status = status.equals("X") ? status : "N";
        return status + " todo " + super.stringDescription();
    }
}
