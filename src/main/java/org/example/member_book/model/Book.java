package org.example.member_book.model;

public class Book {
    private Long id;
    private String title;
    private String author;
    private String genre;
    private boolean available;
    private Member borrowedBy; // null ako nije posuđena

    public Book() {}

    public Book(Long id, String title, String author, String genre, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.available = available;
    }

    // --- Getteri i setteri ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public Member getBorrowedBy() { return borrowedBy; }
    public void setBorrowedBy(Member borrowedBy) { this.borrowedBy = borrowedBy; }
}
