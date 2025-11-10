package org.example.member_book.data;

import org.springframework.stereotype.Component;
import org.example.member_book.model.Member;
import org.example.member_book.model.Book;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class DemoData {

    private final Map<Long, Member> members = new LinkedHashMap<>();
    private final Map<Long, Book> books   = new LinkedHashMap<>();

    private long memberSeq = 1;
    private long bookSeq   = 100;

    public DemoData() {
        // ==== Seed members ====
        saveMember(new Member(null, "Amina Subašić", "amina@example.com", "REGULAR", 2023));
        saveMember(new Member(null, "Imana Purić",   "imana@example.com",  "PREMIUM", 2024));
        saveMember(new Member(null, "Adna Kargić",  "adna@example.com", "REGULAR", 2022));

        // ==== Seed books (početno slobodne) ====
        saveBook(new Book(null, "Na Drini ćuprija", "Ivo Andrić",        "Roman",  true));
        saveBook(new Book(null, "Prokleta avlija",  "Ivo Andrić",        "Roman",  true));
        saveBook(new Book(null, "Zločin i kazna",   "F. M. Dostojevski", "Klasik", true));
        saveBook(new Book(null, "Legenda o Ali-paši",       "Enver Čolaković",      "Roman",     true));
    }

    // ==================== MEMBERS ====================
    public List<Member> findAllMembers() {
        return new ArrayList<>(members.values());
    }

    //To radi pronalaženje člana po ID-ju u memorijskoj mapi i vraća ga “upakovano” u Optional
    public Optional<Member> findMemberById(Long id) {
        return Optional.ofNullable(members.get(id));
    }

    public Member saveMember(Member m) {
        if (m.getId() == null) m.setId(memberSeq++);
        members.put(m.getId(), m);
        return m;
    }

    // ===================== BOOKS =====================
    public List<Book> findAllBooks() {
        return new ArrayList<>(books.values());
    }

    public Optional<Book> findBookById(Long id) {
        return Optional.ofNullable(books.get(id));
    }

    public Book saveBook(Book b) {
        if (b.getId() == null) b.setId(bookSeq++);
        books.put(b.getId(), b);
        return b;
    }

    // ======= Relacija / upiti =========
    public List<Book> availableBooks() {
        return books.values().stream().filter(Book::isAvailable).collect(Collectors.toList());
    }

    public List<Book> findBooksBorrowedBy(Long memberId) {
        return books.values().stream()
                .filter(b -> b.getBorrowedBy() != null && Objects.equals(b.getBorrowedBy().getId(), memberId))
                .collect(Collectors.toList());
    }

    // ============= Akcije (borrow/return) =============
    public boolean borrowBook(Long memberId, Long bookId) {
        var mOpt = findMemberById(memberId);
        var bOpt = findBookById(bookId);
        if (mOpt.isEmpty() || bOpt.isEmpty()) return false;

        Book book = bOpt.get();
        if (!book.isAvailable()) return false; // već posuđena

        book.setAvailable(false);
        book.setBorrowedBy(mOpt.get());
        return true;
    }

    public boolean returnBook(Long bookId) {
        var bOpt = findBookById(bookId);
        if (bOpt.isEmpty()) return false;

        Book book = bOpt.get();
        if (book.isAvailable()) return false; // već je slobodna

        book.setBorrowedBy(null);
        book.setAvailable(true);
        return true;
    }
}
