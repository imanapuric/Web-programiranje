package org.example.member_book.service;

import org.example.member_book.model.Category;
import org.example.member_book.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repo;

    public CategoryService(CategoryRepository repo) { this.repo = repo; }

    public List<Category> findAll() { return repo.findAll(); }
    public Category findById(Long id) { return repo.findById(id).orElse(null); }
    public Category save(Category c) { return repo.save(c); }
    public void delete(Long id) { repo.deleteById(id); }
}
