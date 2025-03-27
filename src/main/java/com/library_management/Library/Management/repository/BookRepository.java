package com.library_management.Library.Management.repository;

import com.library_management.Library.Management.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByBookId(String bookId);
    List<Book> findByTitle(String title);

    void deleteByBookId(String bookId); // Corrected method signature
}
