package org.example.member_book.controller;

import org.example.member_book.model.Book;
import org.example.member_book.model.Member;
import org.example.member_book.service.BookService;
import org.example.member_book.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppController {

    private final MemberService memberService;
    private final BookService bookService;

    public AppController(MemberService memberService, BookService bookService) {
        this.memberService = memberService;
        this.bookService = bookService;
    }

    // HOME -> /members
    @GetMapping("/")
    public String home() {
        return "redirect:/members";
    }

    // LISTA A: Members
    @GetMapping("/members")
    public String members(Model model) {
        model.addAttribute("members", memberService.findAll());
        return "members"; // templates/members.html
    }

    // LISTA B: Books
    @GetMapping("/books")
    public String books(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books"; // templates/books.html
    }

    // STRANICA AKCIJE za člana: dostupne knjige + njegove posuđene
    @GetMapping("/members/action/{id}")
    public String memberAction(@PathVariable Long id, Model model) {
        Member m = memberService.findById(id);
        if (m == null) return "redirect:/members";

        // dostupne
        List<Book> availableBooks = bookService.findAll()
                .stream().filter(Book::isAvailable).toList();

        // posuđene od ovog člana
        List<Book> borrowedByMember = bookService.findAll()
                .stream().filter(b -> !b.isAvailable()
                        && b.getBorrowedBy() != null
                        && b.getBorrowedBy().getId().equals(id)).toList();

        model.addAttribute("member", m);
        model.addAttribute("availableBooks", availableBooks);
        model.addAttribute("borrowedBooks", borrowedByMember);
        return "action"; // templates/action.html
    }

    // POSUDI knjigu
    @PostMapping("/members/action/borrow")
    public String borrowBook(@RequestParam Long memberId, @RequestParam Long bookId) {
        bookService.borrow(memberId, bookId); // servis brine o pravilima
        return "redirect:/members/action/" + memberId;
    }

    // VRATI knjigu
    @PostMapping("/books/return")
    public String returnBook(@RequestParam Long bookId,
                             @RequestParam(required = false) Long memberId) {

        // Ako memberId nije poslan, uzmi ga iz knjige prije vraćanja
        if (memberId == null) {
            Book b = bookService.findById(bookId);
            if (b != null && b.getBorrowedBy() != null) {
                memberId = b.getBorrowedBy().getId();
            }
        }

        bookService.giveBack(bookId);

        return (memberId != null)
                ? "redirect:/members/action/" + memberId
                : "redirect:/books";
    }
}
