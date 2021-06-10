package org.example.app.repository;

import org.apache.log4j.Logger;
import org.example.app.services.IdProvider;
import org.example.web.dto.Book;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookRepository implements ProjectRepository<Book>, ApplicationContextAware {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final Map<String, Book> repo = new HashMap<>();
    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public boolean store(Book book) {
        book.setId(context.getBean(IdProvider.class).provideStringId(book));
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
