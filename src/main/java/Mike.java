import java.util.Scanner;

public class Mike {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ___  ___ __ __            \n"
                + "|   \\/   |__|  | __ ____ \n"
                + "|  |\\/|  |__|  |/  / __ \\ \n"
                + "|  |  |  |  |     < ____/\n"
                + "|__|  |__|__|__|\\__\\____|\n";
        System.out
                .println("Hello! I'm\n" + logo
                        + "\nWhat can I do for you?\n-------------------------------------\n");
        while (true) {
            String temp = sc.next();
            if (temp.equals("bye")) {
                System.out.println(
                        "-------------------------------------\nBye. Hope to see you again soon!\n-------------------------------------\n");
                break;
            }
            System.out.println(
                    "-------------------------------------\n" + temp + "\n-------------------------------------\n");
        }
    }
}
