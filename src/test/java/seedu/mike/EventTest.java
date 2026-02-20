package seedu.mike;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testToString() {
        LocalDateTime start = LocalDateTime.of(2023, 10, 10, 14, 0);
        LocalTime end = LocalTime.of(16, 0);
        Event event = new Event("Team meeting", start, end);

        String expected = "[E][ ] Team meeting (from: Oct 10 2023 14:00 to: 16:00)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testStringDescription() {
        LocalDateTime start = LocalDateTime.of(2023, 10, 10, 14, 0);
        LocalTime end = LocalTime.of(16, 0);
        Event event = new Event("Team meeting", start, end);

        String expected = "N event Team meeting /from 2023-10-10 14:00 /to 16:00";
        assertEquals(expected, event.stringDescription());
    }

    @Test
    public void testCompletedEventToString() {
        LocalDateTime start = LocalDateTime.of(2023, 10, 10, 14, 0);
        LocalTime end = LocalTime.of(16, 0);
        Event event = new Event("Project discussion", start, end);
        event.markAsDone();

        String expected = "[E][X] Project discussion (from: Oct 10 2023 14:00 to: 16:00)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testCompletedEventStringDescription() {
        LocalDateTime start = LocalDateTime.of(2023, 10, 10, 14, 0);
        LocalTime end = LocalTime.of(16, 0);
        Event event = new Event("Project discussion", start, end);
        event.markAsDone();

        String expected = "X event Project discussion /from 2023-10-10 14:00 /to 16:00";
        assertEquals(expected, event.stringDescription());
    }
}
