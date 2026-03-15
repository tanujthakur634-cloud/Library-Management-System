package service;

import database.BookDatabase;
import database.UserDatabase;
import model.Book;
import model.User;
import utility.InputHelper;

import java.util.HashMap;
import java.util.Map;

public class BookService {
    UserDatabase userDatabase;
    BookDatabase bookDatabase;
    String GREEN = "\u001B[32m";
    String RESET = "\u001B[0m";

    public BookService(BookDatabase bookDatabase, UserDatabase userDatabase) {
        this.bookDatabase = bookDatabase;
        this.userDatabase = userDatabase;
    }

    InputHelper inputHelper = new InputHelper();
    int bookSearchChoice;

    //*********************BOOK-SEARCH-----------------------------

    public Book searchBook() {
        System.out.println("\n" + "=".repeat(20) + " BOOK SEARCH " + "=".repeat(20));
        System.out.println(" How would you like to search?");
        System.out.println(" [1] Author Name  [2] Book ID  [3] Book Name");
        System.out.print(" Selection > ");
        bookSearchChoice = inputHelper.getInteger();
        if (bookSearchChoice == 1) {
            System.out.print("Enter Author's Name : ");
            String authorName = inputHelper.getString();
            for (Book book : bookDatabase.getBookRecord().values()) {
                if (book.getAuthor().equalsIgnoreCase(authorName)) {
                    if (book.isAvailable()) {
                        System.out.println("\n" + GREEN + " [✔] BOOK FOUND!" + RESET);
                        book.display();
                        return book;
                    }
                }
            }
        } else if (bookSearchChoice == 2) {
            System.out.print("Enter Book ID : ");
            int id = inputHelper.getInteger();
            Book book = bookDatabase.getBookRecord().get(id);
            if (book != null) {
                if (book.isAvailable()) {
                    System.out.println("\n" + GREEN + " [✔] BOOK FOUND!" + RESET);
                    book.display();
                    return book;
                }
            }
        } else if (bookSearchChoice == 3) {
            System.out.print("Enter Book Name : ");
            String bookname = inputHelper.getString();
            for (Book book : bookDatabase.getBookRecord().values()) {
                if (book.getBookName().equalsIgnoreCase(bookname)) {
                    if (book.isAvailable()) {
                        System.out.println("Book Found");
                        book.display();
                        return book;
                    }
                }
            }

        } else {
            System.out.println("Invalid Choice!!");
        }
        return null;
    }

    //---------------------BOOK-BORROW-------------------------

    public void BookBorrow() {
        Book book = searchBook();
        if (book != null) {
            UserService userService = new UserService(userDatabase);
            User user = userService.checkUser();
            if (user != null) {
                userDatabase.getBorrowedBooks().computeIfAbsent(user, k -> new HashMap<>()).put(book.getId(), book);
                book.setAvailable(false);
                System.out.println("\u001B[32m" + "✔ SUCCESS: " + book.getBookName() + " has been assigned to " + user.getName() + "." + "\u001B[0m");
                try {
                    Thread.sleep(1000); // Wait for 1 second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } else {
                System.out.println("\u001B[31m" + "✖ ERROR: User not found in system." + "\u001B[0m");
            }
        } else {
            System.out.println("Book is Unavailable");
        }
    }

    public void ReturnBook() {
        System.out.print("Enter the ID of the Book You are returning : ");
        int id = inputHelper.getInteger();
        System.out.println("Enter the User ID of the person returning the book : ");
        int userID = inputHelper.getInteger();
        User user = userDatabase.getUser(userID);
        if (user != null) {
            Map<Integer, Book> userBorrowed = userDatabase.getBorrowedBooks().get(user);
            if (userBorrowed != null && userBorrowed.containsKey(id)) {
                userBorrowed.remove(id);
                Book book = bookDatabase.getBookRecord().get(id);
                if (book != null) {
                    book.setAvailable(true);
                    System.out.println("Book returned Successfully");
                    try {
                        Thread.sleep(1000); // Wait for 1 second
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                } else
                    System.out.println("Error: Book found in user's list but missing from the main library database.");
            } else {
                System.out.println("This user does not have this book");
            }
        } else {
            System.out.println("User does not exists");
        }
    }

    public void getAllBooksAvailable() {
        System.out.println("\n" + "=".repeat(60));
        System.out.printf("%-10s | %-25s | %-20s\n", "ID", "BOOK TITLE", "AUTHOR");
        System.out.println("-".repeat(60));

        boolean found = false;
        for (Book book : bookDatabase.getBookRecord().values()) {
            if (book.isAvailable()) {
                System.out.printf("%-10d | %-25s | %-20s\n", book.getId(), truncate(book.getBookName(), 22), truncate(book.getAuthor(), 18));
                found = true;
            }
        }

        if (!found) System.out.println("       --- No books currently available ---");
        System.out.println("=".repeat(60) + "\n");
    }

    // Helper to keep the table neat
    private String truncate(String text, int length) {
        if (text.length() <= length) return text;
        return text.substring(0, length - 3) + "...";
    }
}
