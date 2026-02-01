package seedu.mike;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate deadline;

    Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String stringDescription() {
        String status = super.getStatusIcon();
        status = status.equals("X") ? status : "N";
        return status + " deadline " + super.stringDescription() + " /by "
                + deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
