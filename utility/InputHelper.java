package utility;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHelper {
    Scanner scanner = new Scanner(System.in);
    int integer;
    String string;

    public int getInteger() {
        while (true) {
            try {
                integer = scanner.nextInt();
                scanner.nextLine();
                return integer;
            } catch (InputMismatchException e) {
                System.out.println("\u001B[31m" + " [!] ERROR: Input must be a numeric value. Please try again: " + "\u001B[0m");
            }
        }
    }

    public String getString() {
        string = scanner.nextLine();
        return string;
    }
}
