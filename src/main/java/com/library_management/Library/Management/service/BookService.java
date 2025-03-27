package com.library_management.Library.Management.service;

import com.library_management.Library.Management.exception.BookNotFoundException;
import com.library_management.Library.Management.model.Book;
import com.library_management.Library.Management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Add Book
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    // Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Search by bookId
    public Book getBookById(String bookId) {
        return bookRepository.findByBookId(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book Not Found with ID: " + bookId));
    }

    // Search by title
    public List<Book> getBookByTitle(String title) {
        List<Book> books = bookRepository.findByTitle(title);
        if (books.isEmpty()) {
            throw new BookNotFoundException("No books found with title: " + title);
        }
        return books;
    }

    // Update book details
    public Book updateBook(String bookId, Book updateBook) {
        Book book = bookRepository.findByBookId(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book Not Found with ID: " + bookId));

        if (updateBook.getTitle() != null) book.setTitle(updateBook.getTitle());
        if (updateBook.getAuthor() != null) book.setAuthor(updateBook.getAuthor());
        if (updateBook.getGenre() != null) book.setGenre(updateBook.getGenre());
        if (updateBook.getStatus() != null) book.setStatus(updateBook.getStatus());

        return bookRepository.save(book);
    }

    // Delete Book by ID
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book Not Found with ID: " + id));
        bookRepository.delete(book);
    }

    // Delete Book by bookId
    public void deleteBookByBookId(String bookId) {
        Book book = bookRepository.findByBookId(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book Not Found with ID: " + bookId));
        bookRepository.delete(book);
    }
}
