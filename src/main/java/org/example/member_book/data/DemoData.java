package org.example.member_book.data;

import jakarta.annotation.PostConstruct;
import org.example.member_book.model.Book;
import org.example.member_book.model.Category;
import org.example.member_book.model.Member;
import org.example.member_book.repository.BookRepository;
import org.example.member_book.repository.CategoryRepository;
import org.example.member_book.repository.MemberRepository;
import org.springframework.stereotype.Component;

@Component
public class DemoData {

    private final MemberRepository members;
    private final BookRepository books;
    private final CategoryRepository categories;

    public DemoData(MemberRepository members, BookRepository books, CategoryRepository categories) {
        this.members = members;
        this.books = books;
        this.categories = categories;
    }

    @PostConstruct
    public void loadData() {
        if (members.count() == 0 && books.count() == 0 && categories.count() == 0) {
            // Kategorije
            var roman = categories.save(new Category(null, "Roman", "#f5e0e9"));
            var nauka = categories.save(new Category(null, "Nauka", "#d8f3dc"));
            var istorija = categories.save(new Category(null, "Historija", "#ffe5b4"));

            // Članovi
            var m1 = members.save(new Member(null, "Amina Subašić", "amina@example.com", "REGULAR", 2023));
            var m2 = members.save(new Member(null, "Imana Purić", "imana@example.com", "PREMIUM", 2024));
            var m3 = members.save(new Member(null, "Adna Kargić", "adna@example.com", "REGULAR", 2022));

            // Knjige
            var b1 = new Book(null, "Na Drini ćuprija", "Ivo Andrić", "Roman");
            b1.setCategory(roman);
            books.save(b1);

            var b2 = new Book(null, "Prokleta avlija", "Ivo Andrić", "Roman");
            b2.setCategory(roman);
            books.save(b2);

            var b3 = new Book(null, "Kratka historija vremena", "Stephen Hawking", "Nauka");
            b3.setCategory(nauka);
            books.save(b3);

            var b4 = new Book(null, "Bosna kroz vijekove", "Mustafa Imamović", "Historija");
            b4.setCategory(istorija);
            books.save(b4);
        }
    }
}
