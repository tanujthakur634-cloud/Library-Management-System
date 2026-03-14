package model;

public class Book {
    int ISBN, id;
    String author, bookName, genre;
    boolean isAvailable;

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

    public String getGenre() {
        return genre;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void display() {
        System.out.println("Book Name : " + bookName);
        System.out.println("Author : " + author);
        System.out.println("ISBN : " + ISBN);
        System.out.println("Genre : " + genre);
        System.out.println("Book ID : " + id);
    }
}
