package org.example.member_book.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String membershipLevel; // npr. REGULAR, PREMIUM
    private int yearJoined;

    @Transient // (za sada ne perzistiramo listu; knjige imaju borrowedBy)
    private List<Book> borrowedBooks = new ArrayList<>();

    public Member() {}

    public Member(Long id, String fullName, String email, String membershipLevel, int yearJoined) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.membershipLevel = membershipLevel;
        this.yearJoined = yearJoined;
    }

    // --- getteri/setteri ---
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

    // helperi ostaju identični
    public boolean canBorrowMore() { return borrowedBooks.size() < 3; }
    public void borrowBook(Book b) { if (canBorrowMore()) borrowedBooks.add(b); }
    public void returnBook(Book b) { borrowedBooks.remove(b); }
}
