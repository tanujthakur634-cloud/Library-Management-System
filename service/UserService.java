package service;

import PaymentGateway.UPI;
import PaymentGateway.Wallet;
import database.BookDatabase;
import database.UserDatabase;
import model.Book;
import model.User;
import utility.InputHelper;

import java.util.Map;

public class UserService {

    InputHelper inputHelper = new InputHelper();
    UserDatabase userDatabase ;

    public UserService(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    public void checkBorrowedBooks() {
        Map<User, Map<Integer, Book>> borrowedBooks = userDatabase.getBorrowedBooks();
        User user = checkUser();
        if (user != null) {
            if (borrowedBooks.containsKey(user)) {
                System.out.println("*********LIST_OF_BORROWED_BOOKS***********");
                borrowedBooks.get(user).values().forEach(Book::display);
            } else {
                System.out.println("User does not have any Borrowed Books");
            }
        } else
            System.out.println("User Does not Exist.Add the User First");
    }

    public void addNewUser() {
        System.out.print("Enter User Name : ");
        String name = inputHelper.getString();
        System.out.print("Enter User Phone Number : ");
        String phoneNo = inputHelper.getString();
        int userID = userDatabase.getLastUserID();
        userID++;
        User user = new User(userID, name, phoneNo, 0);
        userDatabase.addUsers(user, userID);
        System.out.println("The new User's ID is : " + userID);
    }

    public User checkUser() {
        System.out.println("Enter UserID : ");
        int userID = inputHelper.getInteger();
        return userDatabase.getUser(userID);
    }

    public void UserDetails() {
        User user = checkUser();
        if (user != null) {
            user.userDetails();
        } else System.out.println("User Does not exists");
    }

    public void printAllUsers() {
        for (Map.Entry<Integer, User> entry : userDatabase.getUsers().entrySet()) {
            User user = entry.getValue();
            user.userDetails();
            System.out.println("----------------------");
        }
    }

    public void checkPaymentDues() throws InterruptedException {
        User user = checkUser();
        if (user != null) {
            if (user.getFine_due() == 0)
                System.out.println("No Payment Dues !! ");
            else {
                System.out.println("Payment Due : " + user.getFine_due());
                System.out.println("Do You want to pay it Now ? (Y/N)");
                char c = inputHelper.getString().charAt(0);
                if (c == 'Y' || c == 'y') {
                    payDues(user);
                } else {
                    System.out.println("You are requested to pay it before due date else penalty will be charged!!");
                }
            }
        } else {
            System.out.println("User Does Not exists");
        }
    }

    public void payDues(User user) throws InterruptedException {
        System.out.print("1.UPI\t2.Wallet\n->Enter The Payment Mode : ");
        int choice = inputHelper.getInteger();
        if (choice == 1) {
            UPI upi = new UPI();
            upi.processPayment(user.getFine_due(), user.getUserID());
            upi.paymentReceipt(user.getFine_due(), user.getUserID());
            user.setFine_due(0);
        } else if (choice == 2) {
            Wallet wallet = new Wallet();
            wallet.processPayment(user.getFine_due(), user.getUserID());
            wallet.paymentReceipt(user.getFine_due(), user.getUserID());
            user.setFine_due(0);
        } else System.out.println("Invalid Choice!!");

    }
}
