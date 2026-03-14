package database;

import model.Book;

import java.util.HashMap;
import java.util.Map;

public class BookDatabase {
    Map<Integer, Book> bookRecord = new HashMap<>();

    public BookDatabase() {
        addBook(new Book(1, 9999, "Tanuj", "Ghost", "Fiction", true));
        addBook(new Book(2, 8888, "Karl", "Biker", "Fiction", true));
        addBook(new Book(3, 7777, "Amar", "God", "Non-Fiction", true));

        addBook(new Book(4, 1011, "J.K. Rowling", "Harry Potter", "Fantasy", true));
        addBook(new Book(5, 2022, "George Orwell", "1984", "Dystopian", true));
        addBook(new Book(6, 3033, "Robert Kiyosaki", "Rich Dad Poor Dad", "Finance", true));
        addBook(new Book(7, 4044, "James Clear", "Atomic Habits", "Self-Help", true));
        addBook(new Book(8, 5055, "F. Scott Fitzgerald", "The Great Gatsby", "Classic", true));
        addBook(new Book(9, 6066, "Agatha Christie", "Death on the Nile", "Mystery", true));
        addBook(new Book(10, 7077, "Carl Sagan", "Cosmos", "Science", true));
    }

    private void addBook(Book book) {
        bookRecord.put(book.getId(), book);
    }

    public Map<Integer, Book> getBookRecord() {
        return bookRecord;
    }

}
