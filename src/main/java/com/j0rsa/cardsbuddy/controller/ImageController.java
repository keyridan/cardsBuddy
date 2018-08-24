package com.j0rsa.cardsbuddy.controller;

import com.j0rsa.cardsbuddy.integration.images.Image;
import com.j0rsa.cardsbuddy.integration.images.ImageSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/me/image")
public class ImageController {
    private ImageSearcher imageSearcher;

    @Autowired
    public ImageController(ImageSearcher imageSearcher) {
        this.imageSearcher = imageSearcher;
    }

    @GetMapping
    @ResponseBody
    public List<Image> deckInfo(@RequestParam String query) {
        return imageSearcher.requestImages(query);
    }
}
