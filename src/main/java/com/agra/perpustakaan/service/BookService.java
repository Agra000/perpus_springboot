package com.agra.perpustakaan.service;

import com.agra.perpustakaan.exception.ResourceNotFoundException;
import com.agra.perpustakaan.model.Book;
import com.agra.perpustakaan.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service = tempat logika bisnis. Controller memanggil service,
 * service memanggil repository (database).
 */
@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    // READ (semua / dengan pencarian)
    public List<Book> findAll(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return repository.findAllByOrderByIdDesc();
        }
        return repository
                .findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrderByIdDesc(keyword, keyword);
    }

    // READ (satu data)
    public Book findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Buku dengan id " + id + " tidak ditemukan"));
    }

    // CREATE
    @Transactional
    public Book create(Book book) {
        book.setId(null); // pastikan selalu insert baru
        return repository.save(book);
    }

    // UPDATE
    @Transactional
    public Book update(Long id, Book data) {
        Book existing = findById(id);
        existing.setTitle(data.getTitle());
        existing.setAuthor(data.getAuthor());
        existing.setPublisher(data.getPublisher());
        existing.setPublishedYear(data.getPublishedYear());
        existing.setStock(data.getStock());
        return repository.save(existing);
    }

    // DELETE
    @Transactional
    public void delete(Long id) {
        Book existing = findById(id);
        repository.delete(existing);
    }
}
