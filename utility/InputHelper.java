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
                System.out.println("Invalid Input\nEnter the value again : ");
            }
        }
    }

    public String getString() {
        string = scanner.nextLine();
        return string;
    }
}
