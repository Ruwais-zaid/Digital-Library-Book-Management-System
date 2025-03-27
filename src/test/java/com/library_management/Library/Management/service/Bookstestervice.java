package com.library_management.Library.Management.service;

import com.library_management.Library.Management.exception.BookNotFoundException;
import com.library_management.Library.Management.model.AvailabilityStatus;
import com.library_management.Library.Management.model.Book;
import com.library_management.Library.Management.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class Bookstestervice {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book book1;
    private Book book2;

    @BeforeEach
    void setUp() {
        book1 = new Book("B001", "Java Basics", "John Doe", "Programming", AvailabilityStatus.AVAILABLE);
        book2 = new Book("B002", "Spring Boot Guide", "Jane Smith", "Technology", AvailabilityStatus.AVAILABLE);
    }

    // TEST ADD BOOK
    @Test
    void testAddBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(book1);
        Book savedBook = bookService.addBook(book1);

        assertNotNull(savedBook);
        assertEquals("Java Basics", savedBook.getTitle()); // Fixed expected value
        verify(bookRepository, times(1)).save(book1);
    }

    // Test Get All Books
    @Test
    void testGetAllBooks() {
        List<Book> bookList = Arrays.asList(book1, book2);
        when(bookRepository.findAll()).thenReturn(bookList);

        List<Book> books = bookService.getAllBooks();

        assertEquals(2, books.size());
        verify(bookRepository, times(1)).findAll();
    }

    // Test Get Book By Id (Success)
    @Test
    void testGetBookById_Success() {
        when(bookRepository.findByBookId("B001")).thenReturn(Optional.of(book1));
        Book foundBook = bookService.getBookById("B001");

        assertNotNull(foundBook);
        assertEquals("Java Basics", foundBook.getTitle());
    }

    // Test Get Book By Id (Not Found)
    @Test
    void testGetBookById_NotFound() {
        when(bookRepository.findByBookId("B999")).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.getBookById("B999"));
    }

    // Test Update Book (Success)
    @Test
    void testUpdateBook_Success() {
        Book updatedBook = new Book("B001", "Advanced Java", "John Doe", "Programming", AvailabilityStatus.AVAILABLE);
        when(bookRepository.findByBookId("B001")).thenReturn(Optional.of(book1));
        when(bookRepository.save(any(Book.class))).thenReturn(updatedBook);

        Book result = bookService.updateBook("B001", updatedBook); // Fixed bookId reference

        assertEquals("Advanced Java", result.getTitle());
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    // Test Update Book (Not Found)
    @Test
    void testUpdateBook_NotFound() {
        when(bookRepository.findByBookId("B999")).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.updateBook("B999", book1));
    }

    // Test Delete Book (Success)
    @Test
    void testDeleteBook_Success() {
        when(bookRepository.findByBookId("B001")).thenReturn(Optional.of(book1));
        doNothing().when(bookRepository).delete(any(Book.class));

        assertDoesNotThrow(() -> bookService.deleteBookByBookId("B001"));

        verify(bookRepository, times(1)).delete(book1); // Fixed delete verification
    }

    // Test Delete Book (Failed)
    @Test
    void testDeleteBook_NotFound() {
        when(bookRepository.findByBookId("B999")).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.deleteBookByBookId("B999"));

        verify(bookRepository, never()).delete(any(Book.class));
    }
}
