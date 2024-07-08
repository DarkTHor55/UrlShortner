package com.UrlShortner.Service;

import com.UrlShortner.Model.Url;
import com.UrlShortner.Repository.UrlRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UrlService {
    @Autowired
    private UrlRepository urlRepository;
    private static final String BASE_URL = "http://localhost:8080/";

    @Transactional
    public String LongToShort(String str) {
        Url existUrl=findUrl(str);
        if(existUrl!=null){
            return BASE_URL +"api/v1/"+ existUrl.getShortUrl();
        }
        String shortUrl = generateShortUrl();
        Url newUrl = new Url();
        newUrl.setOriginalUrl(str);
        newUrl.setShortUrl(shortUrl);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, 10);
        newUrl.setCreatedAt(new Date());
        newUrl.setExpiresAt(calendar.getTime());
        urlRepository.save(newUrl);
        return BASE_URL +"api/v1/"+ shortUrl;
    }
    public String getOrignal(String str){

        Url existUrl=findOriginalUrl(str);
        if(existUrl!=null){
            System.out.println(existUrl.getOriginalUrl());
            return existUrl.getOriginalUrl();
        }
        return null;
    }
    private Url findOriginalUrl(String str){
        List<Url> urls=urlRepository.findAll();
        for (Url u:urls){
            if (u.getShortUrl().equals(str)){
                return u;
            }
        }
        return null;
    }
    private Url findUrl(String str){
        List<Url> urls=urlRepository.findAll();
        for (Url u:urls){
            if (u.getOriginalUrl().equals(str)){
                return u;
            }
        }
        return null;
    }
    private String generateShortUrl() {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder shortUrl = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            shortUrl.append(str.charAt(random.nextInt(str.length())));
        }
        return shortUrl.toString();
    }

}
