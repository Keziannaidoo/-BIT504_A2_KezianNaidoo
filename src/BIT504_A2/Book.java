package BIT504_A2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Book {
    private String id;
    private String isbn;
    private String title;
    private String author;
    private String publicationDate;
    private String genre;
    private int ageRating;
    private boolean isBorrowed;
    private String borrowedMemberId;

    // Constructor
    public Book(String id, String isbn, String title, String author, String publicationDate, String genre, int ageRating, boolean isBorrowed) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.genre = genre;
        this.ageRating = ageRating;
        this.isBorrowed = isBorrowed;
        this.borrowedMemberId = null;
    }
    
    // Methods 
    public String getId() {
        return id;
    }


    public String getIsbn() {
        return isbn;
    }


    public String getTitle() {
        return title;
    }


    public String getAuthor() {
        return author;
    }


    public String getPublicationDate() {
        return publicationDate;
    }


    public String getGenre() {
        return genre;
    }


    public int getAgeRating() {
        return ageRating;
    }
    
    public boolean isBorrowed() { 
        return isBorrowed;
    }
    
    public void setIsBorrowed(boolean status) { 
        this.isBorrowed = status;
    }

	public String getBorrowedMemberId() {
        return borrowedMemberId;
    }

    public void setBorrowedMemberId(String memberId) {
        this.borrowedMemberId = memberId;
        this.setIsBorrowed(memberId != null);
    }

}