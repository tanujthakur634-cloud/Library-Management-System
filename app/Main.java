package app;

import database.BookDatabase;
import database.UserDatabase;
import service.BookService;
import service.UserService;
import utility.InputHelper;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        InputHelper inputHelper = new InputHelper();
        BookDatabase bookDatabase = new BookDatabase();
        UserDatabase userDatabase = new UserDatabase();
        BookService bookService = new BookService(bookDatabase, userDatabase);
        UserService userService = new UserService(userDatabase);
        String RED = "\u001B[31m";
        String RESET = "\u001B[0m";
        String CYAN = "\u001B[36m";
        outer:
        while (true) {
            System.out.println("\n╔══════════════════════════════════════════════════╗");
            System.out.println("║            LIBRARY MANAGEMENT SYSTEM             ║");
            System.out.println("╠══════════════════════════════════════════════════╣");
            System.out.println("║  1. User Service            2. Book Service      ║");
            System.out.println("╚══════════════════════════════════════════════════╝");
            System.out.print(" -> Enter Choice : ");
            int ch = inputHelper.getInteger();
            if (ch == 1) {
                System.out.println("\n---------------------------------------------------------");
                System.out.println("                 USER MANAGEMENT SERVICE                 ");
                System.out.println("---------------------------------------------------------");
                System.out.print("  [1] Add New User            [2] Check Borrowed Books  \n");
                System.out.print("  [3] Check Payment Dues      [4] Check User Details    \n");
                System.out.print("  [5] Print All Users         [0] EXIT                  \n");
                System.out.println("---------------------------------------------------------");
                System.out.print("  Select Choice  > ");
                int choice = inputHelper.getInteger();
                switch (choice) {
                    case 0 -> {
                        System.out.print(CYAN + "Saving session data...");
                        Thread.sleep(700);
                        System.out.print("\rClosing database connections...");
                        Thread.sleep(700);
                        System.out.println("\r" + CYAN + "CLEAN EXIT: All systems offline.          " + RESET);
                        break outer;
                    }
                    case 1 -> {
                        userService.addNewUser();
                    }
                    case 2 -> userService.checkBorrowedBooks();
                    case 3 -> userService.checkPaymentDues();
                    case 4 -> userService.UserDetails();
                    case 5 -> userService.printAllUsers();
                    default -> System.out.println(RED + " [!] Error: Enter a Valid Choice (0-5)!!" + RESET);
                }
            } else if (ch == 2) {
                System.out.println("\n----------- BOOK SERVICE MENU -----------");
                System.out.println("  [1] Search Book         [2] Borrow Book");
                System.out.println("  [3] Return Book         [4] List All Books");
                System.out.println("  [0] EXIT");
                System.out.println("-----------------------------------------");
                System.out.print("  Select an Option: ");
                int choice = inputHelper.getInteger();
                switch (choice) {
                    case 0 -> {

                        System.out.print(CYAN + "Saving session data...");
                        Thread.sleep(700);
                        System.out.print("\rClosing database connections...");
                        Thread.sleep(700);
                        System.out.println("\r" + CYAN + "CLEAN EXIT: All systems offline.          " + RESET);
                        break outer;
                    }
                    case 1 -> {
                        if (bookService.searchBook() == null) System.out.println("Book Unavailable");
                    }
                    case 2 -> bookService.BookBorrow();
                    case 3 -> bookService.ReturnBook();
                    case 4 -> bookService.getAllBooksAvailable();
                    default -> System.out.println(RED + " [!] Error: Enter a Valid Choice (0-5)!!" + RESET);
                }
            } else System.out.println(RED + " [!] Error: Enter a Valid Choice (1-2)!!" + RESET);
        }
    }
}