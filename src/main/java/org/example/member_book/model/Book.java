package org.example.member_book.model;

import jakarta.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String genre;

    private boolean available = true;

    @ManyToOne
    private Member borrowedBy; // može biti null

    @ManyToOne
    private Category category; // novi model C

    public Book() {}

    public Book(Long id, String title, String author, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre  = genre;
        this.available = true;
    }

    // --- getteri/setteri ---
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public boolean isAvailable() { return available; }
    public Member getBorrowedBy() { return borrowedBy; }
    public Category getCategory() { return category; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setAvailable(boolean available) { this.available = available; }
    public void setBorrowedBy(Member borrowedBy) { this.borrowedBy = borrowedBy; }
    public void setCategory(Category category) { this.category = category; }

    // --- helper metode ---
    public boolean canBorrow() { return available; }

    public boolean borrow(Member m) {
        if (!canBorrow()) return false;
        this.available = false;
        this.borrowedBy = m;
        return true;
    }

    public void giveBack() {
        this.available = true;
        this.borrowedBy = null;
    }
}
