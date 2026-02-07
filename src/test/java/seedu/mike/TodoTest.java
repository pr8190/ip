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

}
