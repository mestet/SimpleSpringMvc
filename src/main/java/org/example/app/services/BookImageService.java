package org.example.app.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;

@Service
public class BookImageService {

    Random random = new Random();
    ResourceService resourceService;

    private final Logger logger = Logger.getLogger(BookImageService.class);

    @Autowired
    public BookImageService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    public byte[] getRandomImage() {
        try {
            logger.info("Read random book image");
            Resource[] resources = resourceService.readAllResources("classpath:images/books/*.png");
            Resource resource = resources[random.nextInt(resources.length)];
            return Files.readAllBytes(resource.getFile().toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't get random image", e);
        }
    }

}
