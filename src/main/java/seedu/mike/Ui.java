package seedu.mike;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

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

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLine() {
        System.out.println("-------------------------------------------------\n");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String error) {
        System.out.println("ERROR: " + error);
    }

    public void close() {
        scanner.close();
    }
}