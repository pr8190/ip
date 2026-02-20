package seedu.mike;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void markAsDoneTest() {
        assertEquals("[X] read book", new Task("read book").markAsDone());

        assertEquals("[X] project updation", new Task("project updation").markAsDone());
    }

    @Test
    public void markAsNotDoneTest() {
        Task task = new Task("read book");
        task.markAsDone();
        assertEquals("[ ] read book", task.markAsUndone());
    }

    @Test
    public void toStringTest() {
        Task task = new Task("read book");
        assertEquals("[ ] read book", task.toString());
        task.markAsDone();
        assertEquals("[X] read book", task.toString());
    }
}
