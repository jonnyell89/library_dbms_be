package com.example.library_dbms_be.services;

import com.example.library_dbms_be.dtos.BookRequestDTO;
import com.example.library_dbms_be.dtos.BookResponseDTO;
import com.example.library_dbms_be.mappers.BookMapper;
import com.example.library_dbms_be.models.Book;
import com.example.library_dbms_be.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    public final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // CREATE
    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {

        // Maps the bookRequestDTO to a Book object.
        Book book = BookMapper.toModel(bookRequestDTO);

        // Saves the Book object to the bookRepository.
        Book savedBook = bookRepository.save(book);

        // Maps the Book object to a BookResponseDTO.
        return BookMapper.toBookResponseDTO(savedBook);
    }

    // READ
    public List<BookResponseDTO> getAllBooks() {

        return bookRepository
                .findAll()
                .stream()
                .map(BookMapper::toBookResponseDTO)
                .toList();
    } // Converting Book objects to BookResponseDTOs -> Stream added with help from ChatGPT

    public BookResponseDTO getBookById(Long bookId) {

        // Checks for persisted Book.
        Book book = bookRepository
                .findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with bookId: " + bookId));

        // Maps the Book object to a BookResponseDTO.
        return BookMapper.toBookResponseDTO(book);
    }

    // UPDATE
    public BookResponseDTO updateBookById(Long bookId, BookRequestDTO bookRequestDTO) {

        // Checks for persisted Book.
        Book existingBook = bookRepository
                .findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException(("Book not found with bookId: " + bookId)));

//        if (book.getAvailability() != null) {
//            existingBook.setAvailability(book.getAvailability()); // Sets persisted Book with updated availability.
//        }

        if (bookRequestDTO.getAuthor() != null && !bookRequestDTO.getAuthor().trim().isEmpty()) {
            existingBook.setAuthor(bookRequestDTO.getAuthor().trim()); // Sets persisted Book with updated author.
        }

        if (bookRequestDTO.getTitle() != null && !bookRequestDTO.getTitle().trim().isEmpty()) {
            existingBook.setTitle(bookRequestDTO.getTitle().trim()); // Sets persisted Book with updated title.
        }

        if (bookRequestDTO.getAuthorKey() != null && !bookRequestDTO.getAuthorKey().trim().isEmpty()) {
            existingBook.setAuthorKey(bookRequestDTO.getAuthorKey().trim()); // Sets persisted Book with updated authorKey.
        }

        if (bookRequestDTO.getTitleKey() != null && !bookRequestDTO.getTitleKey().trim().isEmpty()) {
            existingBook.setTitleKey(bookRequestDTO.getTitleKey().trim()); // Sets persisted Book with updated titleKey.
        }

        if (bookRequestDTO.getFirstPublishYear() != null) {
            existingBook.setFirstPublishYear(bookRequestDTO.getFirstPublishYear()); // Sets persisted Book with updated firstPublishYear.
        }

        if (bookRequestDTO.getCover() != null) {
            existingBook.setCover(bookRequestDTO.getCover()); // Sets persisted Book with updated cover.
        }

        if (bookRequestDTO.getCoverEditionKey() != null) {
            existingBook.setCoverEditionKey(bookRequestDTO.getCoverEditionKey()); // Sets persisted Book with updated coverEditionKey.
        }

        // Creates a new row, or updates an existing row, in bookRepository.
        Book updatedBook = bookRepository.save(existingBook);

        // Maps the Book object to a BookResponseDTO.
        return BookMapper.toBookResponseDTO(updatedBook);
    }

    // DELETE
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
