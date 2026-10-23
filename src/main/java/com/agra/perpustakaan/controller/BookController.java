package com.agra.perpustakaan.controller;

import com.agra.perpustakaan.model.Book;
import com.agra.perpustakaan.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API untuk CRUD buku.
 *
 * GET    /api/books          -> Read semua (opsional ?q=kata_kunci)
 * GET    /api/books/{id}     -> Read satu
 * POST   /api/books          -> Create
 * PUT    /api/books/{id}     -> Update
 * DELETE /api/books/{id}     -> Delete
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<Book> getAll(@RequestParam(required = false) String q) {
        return service.findAll(q);
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<Book> create(@Valid @RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(book));
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @Valid @RequestBody Book book) {
        return service.update(id, book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
