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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final Logger logger = Logger.getLogger(BooksController.class);
    private final BookService bookService;

    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/shelf")
    public String bookShelf(Model model) {
        logger.info(this.toString());
        model.addAttribute("book", new Book());
        model.addAttribute("bookList", bookService.getAllBooks());
        return "book_shelf";
    }

    @PostMapping("/save")
    public String saveBook(Book book) {
        logger.info("POST /books/save " + book);
        bookService.saveBook(book);
        return "redirect:/books/shelf";
    }

    @PostMapping("/generate")
    public String generateBook() {
        logger.info("POST /books/generate");
        bookService.generateBook();
        return "redirect:/books/shelf";
    }

    @PostMapping("/remove")
    public String removeBook(@RequestParam(value = "bookId") String bookId, Model model) {
        logger.info("POST /books/remove?bookId=" + bookId);
        if (bookService.removeBookById(bookId)) {
            return "redirect:/books/shelf";
        } else {
            return bookShelf(model);
        }
    }

    @PostMapping("/remove/alike")
    public String removeAlikeBooks(@RequestParam(value = "author") String author,
                                   @RequestParam(value = "title") String title,
                                   @RequestParam(value = "size") String size) {
        logger.info("POST /books/remove/alike?author=" + author
                + "&title=" + title
                + "&size=" + size);
        Book bookToRemove = Book.builder()
                .author(author)
                .title(title)
                .size(size)
                .build();

        bookService.removeAlike(bookToRemove);
        return "redirect:/books/shelf";
    }
}
