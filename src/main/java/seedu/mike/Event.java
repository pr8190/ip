package seedu.mike;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalTime end;

    Event(String description, LocalDateTime start, LocalTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + " to: " +
                end + ")";
    }

    @Override
    public String stringDescription() {
        String status = super.getStatusIcon();
        status = status.equals("X") ? status : "N";
        return status + " event " + super.stringDescription() + " /from "
                + start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + " /to " + end;
    }
}
