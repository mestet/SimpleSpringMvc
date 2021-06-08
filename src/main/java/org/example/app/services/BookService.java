package org.example.app.services;

import org.example.app.repository.ProjectRepository;
import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final ProjectRepository<Book> repository;

    @Autowired
    public BookService(ProjectRepository<Book> repository) {
        this.repository = repository;
    }

    public void saveBook(Book book) {
        if (book.hasAnyAttribute()) {
            repository.store(book);
        }
    }

    public List<Book> getAllBooks() {
        return repository.retrieveAll();
    }

    public boolean removeBookById(Integer bookId) {
        return repository.remove(Book.builder()
                .id(bookId)
                .build());
    }
}
