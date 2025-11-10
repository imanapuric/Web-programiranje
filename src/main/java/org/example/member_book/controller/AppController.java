package org.example.member_book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.example.member_book.data.DemoData;
import org.example.member_book.model.Member;
import org.example.member_book.model.Book;

@Controller
public class AppController {

    private final DemoData data;

    public AppController(DemoData data) {
        this.data = data;
    }

    // HOME -> /members
    @GetMapping("/")
    public String home() {
        return "redirect:/members";
    }

    // LISTA A: Members
    @GetMapping("/members")
    public String members(Model model) {
        model.addAttribute("members", data.findAllMembers());
        return "members"; // templates/members.html
    }

    // LISTA B: Books
    @GetMapping("/books")
    public String books(Model model) {
        model.addAttribute("books", data.findAllBooks());
        return "books"; // templates/books.html
    }

    // STRANICA AKCIJE za člana: prikaz dostupnih knjiga + njegove posuđene
    @GetMapping("/members/action/{id}")
    public String memberAction(@PathVariable Long id, Model model) {
        var memberOpt = data.findMemberById(id);
        if (memberOpt.isEmpty()) {
            return "redirect:/members";
        }
        model.addAttribute("member", memberOpt.get());
        model.addAttribute("availableBooks", data.availableBooks());
        model.addAttribute("borrowedBooks", data.findBooksBorrowedBy(id));
        return "action"; // templates/action.html
    }

    // AKCIJA: POSUDI knjigu (POST, kao u tvojem primjeru "tools/buy")
    @PostMapping("/members/action/borrow")
    public String borrowBook(@RequestParam Long memberId, @RequestParam Long bookId) {
        data.borrowBook(memberId, bookId);
        return "redirect:/members/action/" + memberId;
    }

    // AKCIJA: VRATI knjigu (POST)
    @PostMapping("/books/return")
    public String returnBook(@RequestParam Long bookId, @RequestParam(required = false) Long memberId) {
        // ako memberId nije poslan, pokušaj ga odrediti iz knjige da se vratimo na njegovu stranicu
        var book = data.findBookById(bookId);
        if (memberId == null && book.isPresent() && book.get().getBorrowedBy() != null) {
            memberId = book.get().getBorrowedBy().getId();
        }

        data.returnBook(bookId);
        return (memberId != null)
                ? "redirect:/members/action/" + memberId
                : "redirect:/books";
    }
}
