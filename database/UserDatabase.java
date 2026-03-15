package database;

import model.Book;
import model.User;

import java.util.HashMap;
import java.util.Map;

public class UserDatabase {
    Map<User, Map<Integer, Book>> borrowedBooks = new HashMap<>();
    Map<Integer, User> users = new HashMap<>();

    public UserDatabase() {
        addUser(new User(1, "Marco Rubio", "346646346", 500));
        addUser(new User(2, "Donald Trump", "999999999", 1000));
        addUser(new User(3, "Barack Obama", "444555666", 2500));
        addUser(new User(4, "Steve Jobs", "101010101", 0));
        addUser(new User(5, "Bill Gates", "202020202", 5000));
        addUser(new User(6, "Elon Musk", "303030303", 10000));
        addUser(new User(7, "Mark Zuckerberg", "404040404", 150));
        addUser(new User(8, "Jeff Bezos", "505050505", 8000));
        addUser(new User(9, "Satya Nadella", "606060606", 1200));
        addUser(new User(10, "Sundar Pichai", "707070707", 1100));
    }

    public void addUser(User user) {
        users.put(user.getUserID(), user);
    }

    public Map<Integer, User> getUsers() {
        return users;
    }


    public Map<User, Map<Integer, Book>> getBorrowedBooks() {
        return borrowedBooks;
    }


    public void addUsers(User user, int userID) {
        users.put(userID, user);

    }

    public int getLastUserID() {
        return users.keySet().stream().max(Integer::compareTo).orElse(0);
    }

    public User getUser(int id) {
        return users.get(id);
    }
}
