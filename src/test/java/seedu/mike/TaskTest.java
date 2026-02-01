package seedu.mike;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.beans.Transient;

public class TaskTest {
    @Test
    public void markAsDoneTest() {
        assertEquals("[X] read book", new Task("read book").markAsDone());

        assertEquals("[X] project updation", new Task("project updation").markAsDone());
    }
}
