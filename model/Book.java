package model;

public class Book {
    private final int ISBN, id;
    private final String author, bookName, genre;
    private boolean isAvailable;

    public Book(int id, int ISBN, String author, String bookName, String genre, boolean isAvailable) {
        this.ISBN = ISBN;
        this.isAvailable = isAvailable;
        this.author = author;
        this.bookName = bookName;
        this.genre = genre;
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public int getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void display() {
        System.out.println("\n--- BOOK INFORMATION ---");
        System.out.printf("  %-12s : %s\n", "TITLE", bookName);
        System.out.printf("  %-12s : %s\n", "AUTHOR", author);
        System.out.printf("  %-12s : %d\n", "BOOK ID", id);
        System.out.printf("  %-12s : %d\n", "ISBN", ISBN);
        System.out.printf("  %-12s : %s\n", "GENRE", genre);
        System.out.println("------------------------");
    }
}
