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
    public BookService(BookDatabase bookDatabase,UserDatabase userDatabase) {
        this.bookDatabase = bookDatabase;
        this.userDatabase=userDatabase;
    }

    InputHelper inputHelper = new InputHelper();
    int bookSearchChoice;

    //*********************BOOK-SEARCH-----------------------------

    public Book searchBook() {
        System.out.println("Do you want to search the book via Author Name(1) or Book Id(2)  or BookName(3) ? ");
        bookSearchChoice=inputHelper.getInteger();
        if (bookSearchChoice == 1) {
            System.out.print("Enter Author's Name : ");
            String authorName = inputHelper.getString();
            for (Book book : bookDatabase.getBookRecord().values()) {
                if (book.getAuthor().equalsIgnoreCase(authorName)) {
                    if (book.isAvailable()) {
                        System.out.println("Book Found");
                        book.display();
                        return book;
                    }
                }
            }
        } else if (bookSearchChoice == 2) {
            System.out.print("Enter Book ID : ");
            int id = inputHelper.getInteger();
            Book book = bookDatabase.getBookRecord().get(id);
            if (book!=null){
                if (book.isAvailable()) {
                    System.out.println("Book Found");
                    book.display();
                    return book;
                }
            }
        }

     else if(bookSearchChoice==3) {
            System.out.print("Enter Book Name : ");
            String bookname=inputHelper.getString();
            for(Book book : bookDatabase.getBookRecord().values()){
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
        Book book=searchBook();
        if (book!=null) {
            UserService userService = new UserService(userDatabase);
            User user = userService.checkUser();
            if(user!=null) {
                userDatabase.getBorrowedBooks().computeIfAbsent(user,k->new HashMap<>()).put(book.getId(),book);
                book.setAvailable(false);
                System.out.println("Book Borrowed Successfully");
            }
            else{
                System.out.println("User Not Found ! First add the User to Library Database!!");
            }
        }
        else{
            System.out.println("Book is Unavailable");
        }
    }

    public void ReturnBook() {
        System.out.print("Enter the ID of the Book You are returning : ");
        int id = inputHelper.getInteger();
        System.out.println("Enter the User ID of the person returning the book : ");
        int userID = inputHelper.getInteger();
        User user = userDatabase.getUser(userID);
        if (user!=null) {
            Map<Integer, Book> userBorrowed = userDatabase.getBorrowedBooks().get(user);
            if (userBorrowed != null && userBorrowed.containsKey(id)) {
                userBorrowed.remove(id);
                Book book = bookDatabase.getBookRecord().get(id);
                if (book != null) {
                    book.setAvailable(true);
                    System.out.println("Book returned Successfully");
                }
                else
                    System.out.println("Error: Book found in user's list but missing from the main library database.");
            } else {
                System.out.println("This user does not have this book");
            }
        }
        else{
            System.out.println("User does not exists");
        }
    }

    public void getAllBooksAvailable(){
        for (Book book : bookDatabase.getBookRecord().values()){
            if (book.isAvailable()) {
                book.display();
                System.out.println("------------------------------");
            }
        }
    }
}
