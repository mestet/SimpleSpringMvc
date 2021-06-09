package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.app.repository.BookRepository;
import org.example.app.utils.RandomUtils;
import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final Logger logger = Logger.getLogger(BookService.class);

    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public void saveBook(Book book) {
        if (book.hasAnyAttribute()) {
            repository.store(book);
        } else {
            logger.info("Attempt to save book without any attributes");
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

    public void removeAlike(Book bookWithRegex) {
        List<Book> bookList = repository.retrieveAll();
        for (Book book : bookList) {
            if (booksAlike(bookWithRegex, book)) {
                repository.remove(book);
            }
        }
    }

    private boolean booksAlike(Book example, Book candidate) {
        return checkNotNullAndMatch(example.getAuthor(), candidate.getAuthor())
                || checkNotNullAndMatch(example.getTitle(), candidate.getTitle())
                || checkNotNullAndMatch(example.getSize(), candidate.getSize());
    }

    private boolean checkNotNullAndMatch(String regex, String candidate) {
        if (regex == null || regex.isEmpty() || candidate == null) return false;
        return candidate.matches(regex);
    }

    public void generateBook() {
        saveBook(Book.builder()
                .author(RandomUtils.nextEngString(12))
                .title(RandomUtils.nextEngString(10))
                .size(RandomUtils.nextNumericString(4))
                .build());
    }
}
