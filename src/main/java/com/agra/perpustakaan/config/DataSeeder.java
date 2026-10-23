package com.agra.perpustakaan.config;

import com.agra.perpustakaan.model.Book;
import com.agra.perpustakaan.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Mengisi beberapa data contoh saat aplikasi pertama kali jalan (tabel masih kosong).
 */
@Component
public class DataSeeder implements CommandLineRunner {

    private final BookRepository repository;

    public DataSeeder(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() > 0) return;

        repository.save(book("Laskar Pelangi", "Andrea Hirata", "Bentang Pustaka", 2005, 5));
        repository.save(book("Bumi Manusia", "Pramoedya Ananta Toer", "Hasta Mitra", 1980, 3));
        repository.save(book("Clean Code", "Robert C. Martin", "Prentice Hall", 2008, 2));
    }

    private Book book(String title, String author, String publisher, int year, int stock) {
        Book b = new Book();
        b.setTitle(title);
        b.setAuthor(author);
        b.setPublisher(publisher);
        b.setPublishedYear(year);
        b.setStock(stock);
        return b;
    }
}
