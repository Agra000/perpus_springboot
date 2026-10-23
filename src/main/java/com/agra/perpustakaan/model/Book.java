package com.agra.perpustakaan.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

/**
 * Entity = representasi tabel "books" di database.
 * Setiap field di bawah menjadi kolom di tabel.
 */
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long id;

    @NotBlank(message = "Judul wajib diisi")
    @Size(max = 150, message = "Judul maksimal 150 karakter")
    @Column(nullable = false, length = 150)
    private String title;

    @NotBlank(message = "Penulis wajib diisi")
    @Size(max = 100, message = "Penulis maksimal 100 karakter")
    @Column(nullable = false, length = 100)
    private String author;

    @Size(max = 100)
    private String publisher;

    @Min(value = 1000, message = "Tahun terbit tidak valid")
    @Max(value = 2100, message = "Tahun terbit tidak valid")
    private Integer publishedYear;

    @NotNull(message = "Stok wajib diisi")
    @PositiveOrZero(message = "Stok tidak boleh negatif")
    @Column(nullable = false)
    private Integer stock;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // ===== Getter & Setter =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public Integer getPublishedYear() { return publishedYear; }
    public void setPublishedYear(Integer publishedYear) { this.publishedYear = publishedYear; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
