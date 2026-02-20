package seedu.mike;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testToString() {
        Deadline deadline = new Deadline("Submit assignment", LocalDate.of(2023, 10, 31));
        assertEquals("[D][ ] Submit assignment (by: Oct 31 2023)", deadline.toString());
    }

    @Test
    public void testStringDescription() {
        Deadline deadline = new Deadline("Submit assignment", LocalDate.of(2023, 10, 31));
        assertEquals("N deadline Submit assignment /by 2023-10-31", deadline.stringDescription());
    }

    @Test
    public void testToStringWithCompletedTask() {
        Deadline deadline = new Deadline("Complete project", LocalDate.of(2023, 11, 15));
        deadline.markAsDone();
        assertEquals("[D][X] Complete project (by: Nov 15 2023)", deadline.toString());
    }

    @Test
    public void testStringDescriptionWithCompletedTask() {
        Deadline deadline = new Deadline("Complete project", LocalDate.of(2023, 11, 15));
        deadline.markAsDone();
        assertEquals("X deadline Complete project /by 2023-11-15", deadline.stringDescription());
    }
}
