package com.UrlShortner.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 1000)
    private String originalUrl;
    @Column(length = 1000)
    private String shortUrl;
    private Date createdAt;
    private Date expiresAt;
}
