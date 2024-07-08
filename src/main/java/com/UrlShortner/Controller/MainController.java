package com.UrlShortner.Controller;

import com.UrlShortner.Model.ShortenRequest;
import com.UrlShortner.Service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("/api/v1")
@RestController
public class MainController {
    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    public String getShorten(@RequestBody ShortenRequest url) {
        return urlService.LongToShort(url.getOriginalUrl());
    }
    @GetMapping("/{shortUrl}")
    public void redirectToOriginalUrl(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
        String originalUrl = urlService.getOrignal(shortUrl);
        if (originalUrl != null) {
            response.sendRedirect(originalUrl);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_GATEWAY);
        }
    }



}
