package org.example.member_book.model;

import jakarta.persistence.*;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String colorHex;

    public Category() {}

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(Long id, String name, String colorHex) {
        this.id = id;
        this.name = name;
        this.colorHex = colorHex;
    }

    // --- getteri/setteri ---
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getColorHex() { return colorHex; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setColorHex(String colorHex) { this.colorHex = colorHex; }
}
