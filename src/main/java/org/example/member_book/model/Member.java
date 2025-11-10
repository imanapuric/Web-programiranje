package org.example.member_book.model;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private Long id;
    private String fullName;
    private String email;
    private String membershipLevel; // npr. REGULAR, PREMIUM
    private int yearJoined;
    private List<Book> borrowedBooks = new ArrayList<>();

    public Member() {}

    //konstruktor
    public Member(Long id, String fullName, String email, String membershipLevel, int yearJoined) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.membershipLevel = membershipLevel;
        this.yearJoined = yearJoined;
    }

    // --- Getteri i setteri ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMembershipLevel() { return membershipLevel; }
    public void setMembershipLevel(String membershipLevel) { this.membershipLevel = membershipLevel; }

    public int getYearJoined() { return yearJoined; }
    public void setYearJoined(int yearJoined) { this.yearJoined = yearJoined; }

    public List<Book> getBorrowedBooks() { return borrowedBooks; }
    public void setBorrowedBooks(List<Book> borrowedBooks) { this.borrowedBooks = borrowedBooks; }

    // ne može zadužiti više od 3 knjige
    public boolean canBorrowMore() {
        return borrowedBooks.size() < 3;
    }

    //dodajemo zaduzenu knjigu
    public void borrowBook(Book b) {
        if (canBorrowMore()) borrowedBooks.add(b);
    }

    public void returnBook(Book b) {
        borrowedBooks.remove(b);
    }
}
