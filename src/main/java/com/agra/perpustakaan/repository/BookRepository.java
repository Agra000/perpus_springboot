package com.agra.perpustakaan.repository;

import com.agra.perpustakaan.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * JpaRepository sudah menyediakan method CRUD:
 * save(), findAll(), findById(), deleteById(), dll.
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    // Query otomatis dari nama method: cari berdasarkan judul ATAU penulis
    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrderByIdDesc(
            String title, String author);

    List<Book> findAllByOrderByIdDesc();
}
