package org.example.app.repository;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepository implements ProjectRepository<Book> {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final Map<Integer, Book> repo = new HashMap<>();

    @Override
    public boolean store(Book book) {
        book.setId(book.hashCode());
        logger.info("Store new book: " + book);
        repo.put(book.getId(), book);
        return true;
    }

    @Override
    public List<Book> retrieveAll() {
        return new ArrayList<>(repo.values());
    }

    @Override
    public boolean remove(Book book) {
        logger.info("Deleting book by id: " + book.getId());
        return repo.remove(book.getId()) != null;
    }
}
