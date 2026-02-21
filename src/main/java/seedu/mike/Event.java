package seedu.mike;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that is an event which has a start time and an end time
 */
public class Event extends Task {
    /** Stores the start time of the event in the format yyyy-MM-dd HH:mm */
    protected LocalDateTime start;

    /** Stores the end time of the event in the format HH:mm */
    protected LocalTime end;

    /**
     * Creates a new Event object
     *
     * @param description
     * @param start
     * @param end
     */
    Event(String description, LocalDateTime start, LocalTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + " to: "
                + end + ")";
    }

    @Override
    public String stringDescription() {
        String status = super.getStatusIcon();
        status = status.equals("X") ? status : "N";
        return status + " event " + super.stringDescription() + " /from "
                + start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + " /to " + end;
    }

    /**
     * Checks if the event is happening within the next 3 days.
     *
     * @return true if the event is scheduled to occur within the next 3 days, false
     *         otherwise
     */
    public boolean isHappeningSoon() {
        LocalDateTime now = LocalDateTime.now();
        return !start.isBefore(now) && !start.isAfter(now.plusDays(3));
    }
}
