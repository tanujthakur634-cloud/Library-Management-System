package model;

import java.util.Objects;

public class User {
    private final int userID;
    private final String name;
    private final String phoneNo;
    private int fine_due;

    public User(int userID, String name, String phoneNo, int fine_due) {
        this.userID = userID;
        this.name = name;
        this.phoneNo = phoneNo;
        this.fine_due = fine_due;
    }

    public int getUserID() {
        return userID;
    }

    public int getFine_due() {
        return fine_due;
    }

    public void setFine_due(int fine_due) {
        this.fine_due = fine_due;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userID == user.userID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, name, phoneNo, fine_due);
    }

    public void userDetails() {
        System.out.println("\n+-------------------------------------------+");
        System.out.println("  USER PROFILE : ID #" + userID);
        System.out.println("+-------------------------------------------+");
        System.out.printf("  NAME    : %s\n", name);
        System.out.printf("  PHONE   : %s\n", phoneNo);
        System.out.printf("  BALANCE : ₹%.2f\n", fine_due);
        System.out.println("+-------------------------------------------+");
    }

    public String getName() {
        return name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
}
