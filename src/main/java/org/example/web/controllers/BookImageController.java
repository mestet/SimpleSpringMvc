package org.example.web.controllers;

import org.example.app.services.BookImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/images")
public class BookImageController {

    private final BookImageService bookImageService;

    @Autowired
    public BookImageController(BookImageService bookImageService) {
        this.bookImageService = bookImageService;
    }

    @GetMapping("/book/random")
    public ResponseEntity<byte[]> bookImage() {
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache());
        headers.setContentType(MediaType.IMAGE_PNG);

        byte[] media = bookImageService.getRandomImage();
        return new ResponseEntity<>(media, headers, HttpStatus.OK);
    }
}
