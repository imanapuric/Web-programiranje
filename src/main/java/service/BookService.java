package org.example.member_book.service;

import org.example.member_book.model.Book;
import org.example.member_book.model.Member;
import org.example.member_book.repository.BookRepository;
import org.example.member_book.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository books;
    private final MemberRepository members;

    public BookService(BookRepository books, MemberRepository members) {
        this.books = books; this.members = members;
    }

    public List<Book> findAll() { return books.findAll(); }
    public Book findById(Long id) { return books.findById(id).orElse(null); }
    public Book save(Book b) { return books.save(b); }
    public void delete(Long id) { books.deleteById(id); }

    // jednostavne akcije (Lab1->Lab2)
    public boolean borrow(Long memberId, Long bookId) {
        Book b = findById(bookId);
        Member m = members.findById(memberId).orElse(null);
        if (b == null || m == null || !b.canBorrow()) return false;
        b.borrow(m);
        books.save(b);
        return true;
    }

    public void giveBack(Long bookId) {
        Book b = findById(bookId);
        if (b != null) { b.giveBack(); books.save(b); }
    }
}
