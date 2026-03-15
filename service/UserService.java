package service;

import PaymentGateway.PaymentGateway;
import PaymentGateway.UPI;
import PaymentGateway.Wallet;
import database.UserDatabase;
import model.Book;
import model.User;
import utility.InputHelper;

import java.util.Map;

public class UserService {

    InputHelper inputHelper = new InputHelper();
    UserDatabase userDatabase;
    String RED = "\u001B[31m";
    String RESET = "\u001B[0m";
    String GREEN = "\u001B[32m";

    public UserService(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    public void checkBorrowedBooks() {
        Map<User, Map<Integer, Book>> borrowedBooks = userDatabase.getBorrowedBooks();
        User user = checkUser();
        if (user != null) {
            if (borrowedBooks.containsKey(user)) {
                System.out.println("\n📚 BOOKS CURRENTLY WITH " + user.getName().toUpperCase() + ":");
                int count = 1;
                for (Book book : borrowedBooks.get(user).values()) {
                    System.out.printf("   %d. [%d] %s by %s\n", count++, book.getId(), book.getBookName(), book.getAuthor());
                }
                System.out.println();
            } else {
                System.out.println("\n[ℹ] Info: " + user.getName() + " has no active borrowings.");
            }
        } else System.out.println(RED + " [!] ERROR: User does not exist." + RESET);
    }

    public void addNewUser() {
        System.out.print("  Enter User Name : ");
        String name = inputHelper.getString();
        System.out.print("  Enter User Phone Number : ");
        String phoneNo = inputHelper.getString();
        int userID = userDatabase.getLastUserID();
        userID++;
        User user = new User(userID, name, phoneNo, 0);
        userDatabase.addUsers(user, userID);
        System.out.println("\n[✔] SUCCESS: New User Created Successfully!");
        System.out.println("    Assigned ID: " + userID);
    }

    public User checkUser() {
        System.out.print("  Enter UserID : ");
        int userID = inputHelper.getInteger();
        return userDatabase.getUser(userID);
    }

    public void UserDetails() {
        User user = checkUser();
        if (user != null) {
            user.userDetails();
        } else System.out.println(RED + " [!] ERROR: User ID not found in the database." + RESET);
    }

    public void printAllUsers() {
        System.out.println("\n" + "=".repeat(65));
        System.out.printf("%-5s | %-20s | %-15s | %-10s\n", "ID", "USER NAME", "PHONE", "DUES");
        System.out.println("-".repeat(65));

        for (User user : userDatabase.getUsers().values()) {
            System.out.printf("%-5d | %-20s | %-15s | ₹%-10d\n", user.getUserID(), user.getName(), user.getPhoneNo(), user.getFine_due());
        }
        System.out.println("=".repeat(65));
    }

    public void checkPaymentDues() throws InterruptedException {
        User user = checkUser();
        if (user != null) {
            if (user.getFine_due() == 0)
                System.out.println("\n" + GREEN + " [✔] SUCCESS: Account Clear. No outstanding dues found!" + RESET);
            else {
                System.out.println("\u001B[33m" + "⚠️  ATTENTION: You have an outstanding balance of ₹" + user.getFine_due() + "\u001B[0m");
                System.out.print(" Would you like to settle this now? (Y/N): ");

                char c = inputHelper.getString().charAt(0);
                if (c == 'Y' || c == 'y') {
                    payDues(user);
                } else {
                    System.out.println("\u001B[33m" + "⚠️  ATTENTION: You are requested to pay your dues urgently " + "\u001B[0m");
                }
            }
        } else {
            System.out.println(RED + " [!] ERROR: User ID not found in the database." + RESET);
        }
    }

    public void payDues(User user) throws InterruptedException {
        System.out.println("\n--- SELECT PAYMENT METHOD ---");
        System.out.printf("  [1] %-10s [2] %-10s\n", "UPI", "WALLET");
        System.out.print("  Payment Mode > ");
        int choice = inputHelper.getInteger();

        PaymentGateway paymentGateway;

        if (choice == 1) {
            paymentGateway = new UPI();
        } else if (choice == 2) {
            paymentGateway = new Wallet();
        } else {
            System.out.println(RED + " [!] Error: Enter a Valid Choice (1-2)!!" + RESET);
            return;
        }

        paymentGateway.processPayment(user.getFine_due(), user.getUserID());
        paymentGateway.paymentReceipt(user.getFine_due(), user.getUserID(), user);

        user.setFine_due(0);
    }
}
