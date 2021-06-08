package org.example.app.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ResourceService {

    ResourcePatternResolver resourcePatternResolver;

    private final Logger logger = Logger.getLogger(ResourceService.class);

    @Autowired
    public ResourceService(ResourcePatternResolver resourcePatternResolver) {
        this.resourcePatternResolver = resourcePatternResolver;
    }

    public Resource readResource(String resourcePath) {
        logger.info("Read file from classpath:" + resourcePath);
        return new ClassPathResource(resourcePath);
    }

    public Resource[] readAllResources(String folderPath) {
        try {
            return resourcePatternResolver.getResources(folderPath);
        } catch (IOException e) {
            throw new RuntimeException("Error while reading resources from folder: " + folderPath, e);
        }
    }
}
