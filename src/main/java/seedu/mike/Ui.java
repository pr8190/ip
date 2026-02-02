package seedu.mike;

import java.util.Scanner;

/**
 * Handles all user interface interactions for the Mike chatbot.
 * This class is responsible for displaying messages to the user and reading
 * user input.
 */
public class Ui {

    /** Scanner object for reading user input from the console. */
    private Scanner scanner;

    /**
     * Constructs a Ui object and initializes the scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message with the Mike logo when the program starts.
     * Shows the chatbot's ASCII art logo and a greeting message.
     */
    public void showWelcome() {
        String logo = " ___  ___ __ __            \n"
                + "|   \\/   |__|  | __ ____ \n"
                + "|  |\\/|  |__|  |/  / __ \\ \n"
                + "|  |  |  |  |     < ____/\n"
                + "|__|  |__|__|__|\\__\\____|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        this.showLine();
    }

    /**
     * Displays the goodbye message when the user exits the program.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a horizontal line separator for visual clarity in the console
     * output.
     */
    public void showLine() {
        System.out.println("-------------------------------------------------\n");
    }

    /**
     * Reads a command from the user via the console.
     * 
     * @return The command string entered by the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a message to the user.
     * 
     * @param message The message to be displayed
     */
    public void showMessage(String message) {
        System.out.println("-------------------------------------------------\n");
        System.out.println(message);
        System.out.println("-------------------------------------------------\n");
    }

    /**
     * Closes the scanner to free up system resources.
     * This method should be called when the program is terminating.
     */
    public void close() {
        scanner.close();
    }
}