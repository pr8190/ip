package seedu.mike;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void stringDescriptionTest() {
        assertEquals("N todo read book", new Todo("read book").stringDescription());

        assertEquals("N todo project updation", new Todo("project updation").stringDescription());

        assertEquals("N todo product testing", new Todo("product testing").stringDescription());
    }

    @Test
    public void markAsDoneTest() {
        Todo todo = new Todo("read book");
        todo.markAsDone();
        assertEquals("[T][X] read book", todo.toString());
    }

    @Test
    public void markAsNotDoneTest() {
        Todo todo = new Todo("read book");
        todo.markAsDone();
        todo.markAsUndone();
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void toStringTest() {
        Todo todo = new Todo("read book");
        assertEquals("[T][ ] read book", todo.toString());
        todo.markAsDone();
        assertEquals("[T][X] read book", todo.toString());
    }
}
