package org.example.app.services;

import org.example.app.repository.BookRepository;
import org.example.app.repository.ProjectRepository;
import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final ProjectRepository<Book> bookRepo;

    @Autowired
    public BookService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public void saveBook(Book book) {
        if (book.hasAttributes()) {
            bookRepo.store(book);
        }
    }

    public List<Book> getAllBooks() {
        return bookRepo.retrieveAll();
    }

    public boolean removeBookById(Integer bookId) {
        return bookRepo.removeById(bookId);
    }
}
