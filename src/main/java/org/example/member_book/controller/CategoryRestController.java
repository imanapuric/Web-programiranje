package org.example.member_book.controller;

import org.example.member_book.model.Category;
import org.example.member_book.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {
    private final CategoryService categories;

    public CategoryRestController(CategoryService categories) { this.categories = categories; }

    @GetMapping
    public List<Category> all() { return categories.findAll(); }

    @PostMapping
    public Category create(@RequestBody Category c) { return categories.save(c); }
}
