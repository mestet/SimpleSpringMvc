package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.BookService;
import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books/search")
public class BookSearchController {

    private final Logger logger = Logger.getLogger(BookSearchController.class);
    private final BookService bookService;

    @Autowired
    public BookSearchController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String searchPage(Model model) {
        model.addAttribute("searchResultList", bookService.getAllBooks());
        model.addAttribute("criteriaBook", Book.builder().build());
        return "book_search";
    }

    @PostMapping
    public String searchBooks(Book criteriaBook, Model model) {
        logger.info("POST /books/search like" + criteriaBook);

        List<Book> searchResultList = bookService.findBookAlike(criteriaBook);
        model.addAttribute("searchResultList", searchResultList);
        model.addAttribute("criteriaBook", criteriaBook);
        return "book_search";
    }
}
