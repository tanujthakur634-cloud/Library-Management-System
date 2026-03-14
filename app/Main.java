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

        outer:
        while (true) {
            System.out.println("\n**************LIBRARY_MANAGEMENT_SYSTEM*************\n");
            System.out.print("USER_SERVICE(1)\tBOOK_SERVICE(2)\n->Enter Your Choice : ");
            int ch = inputHelper.getInteger();
            if (ch == 1) {
                System.out.println("0.Exit\t1.Add New User\t2.Check Borrowed Books\t3.Check if any payment dues\t4.Check User Details\t5.Print all Users\n->Enter Your Choice : ");
                int choice = inputHelper.getInteger();
                switch (choice) {
                    case 0 -> {
                        System.out.println("Exiting...");
                        break outer;
                    }
                    case 1 -> {
                        userService.addNewUser();
                    }
                    case 2 -> userService.checkBorrowedBooks();
                    case 3 -> userService.checkPaymentDues();
                    case 4 -> userService.UserDetails();
                    case 5 -> userService.printAllUsers();
                    default -> System.out.println("Enter a Valid Choice!!");
                }
            } else if (ch == 2) {
                System.out.println("0.Exit\t1.Search Book\t2.Borrow Book\t3.Return Book\t4.List of all Books Available\n->Enter Your Choice : ");
                int choice = inputHelper.getInteger();
                switch (choice) {
                    case 0 -> {
                        System.out.println("Exiting...");
                        break outer;
                    }
                    case 1 -> {
                        if (bookService.searchBook() == null) System.out.println("Book Unavailable");
                    }
                    case 2 -> bookService.BookBorrow();
                    case 3 -> bookService.ReturnBook();
                    case 4 -> bookService.getAllBooksAvailable();
                    default -> System.out.println("Enter a Valid Choice!!");
                }
            } else
                System.out.println("Enter a Valid Choice!!");
        }
    }
}