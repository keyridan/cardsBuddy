package com.j0rsa.cardsbuddy.controller;

import com.j0rsa.cardsbuddy.integration.images.ImageSearcher;
import com.j0rsa.cardsbuddy.integration.images.Images;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/image")
public class ImageController {
    private ImageSearcher imageSearcher;

    @Autowired
    public ImageController(ImageSearcher imageSearcher) {
        this.imageSearcher = imageSearcher;
    }

    @GetMapping
    @ResponseBody
    public Images deckInfo(@RequestParam String query, @RequestParam(required = false, defaultValue = "1") int pageNumber) {
        return imageSearcher.requestImages(query, pageNumber);
    }
}
