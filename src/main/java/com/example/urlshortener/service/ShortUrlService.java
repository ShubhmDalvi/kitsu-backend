package com.example.urlshortener.service;

import com.example.urlshortener.dto.ShortUrlResponse;
import com.example.urlshortener.entity.ShortUrl;
import com.example.urlshortener.exception.NotFoundException;
import com.example.urlshortener.repository.ShortUrlRepository;
import com.example.urlshortener.util.ShortCodeGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class ShortUrlService {

    private final ShortUrlRepository repository;

    public ShortUrlService(ShortUrlRepository repository) {
        this.repository = repository;
    }

    public ShortUrlResponse create(String originalUrl) {
        // Basic validation (additional validation can be added)
        if (originalUrl == null || originalUrl.isBlank()) {
            throw new IllegalArgumentException("url is required");
        }

        // Generate unique shortCode (retry on rare collision)
        String shortCode;
        int tries = 0;
        do {
            shortCode = ShortCodeGenerator.generate();
            tries++;
            if (tries > 10) {
                // fallback to longer code
                shortCode = ShortCodeGenerator.generate(8);
            }
        } while (repository.existsByShortCode(shortCode));

        ShortUrl ent = new ShortUrl();
        ent.setUrl(originalUrl.trim());
        ent.setShortCode(shortCode);
        ent.setCreatedAt(OffsetDateTime.now());
        ent.setUpdatedAt(OffsetDateTime.now());
        ShortUrl saved = repository.save(ent);
        return toDto(saved);
    }

    public ShortUrlResponse getByShortCode(String shortCode) {
        ShortUrl ent = repository.findByShortCode(shortCode)
                .orElseThrow(() -> new NotFoundException("Short URL not found"));
        return toDto(ent);
    }

    @Transactional
    public ShortUrlResponse incrementAndGet(String shortCode) {
        ShortUrl ent = repository.findByShortCode(shortCode)
                .orElseThrow(() -> new NotFoundException("Short URL not found"));
        ent.incrementAccessCount();
        ent.setUpdatedAt(OffsetDateTime.now());
        // because of @Transactional and JPA, changes will flush
        return toDto(ent);
    }

    public ShortUrlResponse update(String shortCode, String newUrl) {
        ShortUrl ent = repository.findByShortCode(shortCode)
                .orElseThrow(() -> new NotFoundException("Short URL not found"));
        ent.setUrl(newUrl.trim());
        ent.setUpdatedAt(OffsetDateTime.now());
        ShortUrl saved = repository.save(ent);
        return toDto(saved);
    }

    public void delete(String shortCode) {
        ShortUrl ent = repository.findByShortCode(shortCode)
                .orElseThrow(() -> new NotFoundException("Short URL not found"));
        repository.delete(ent);
    }

    private ShortUrlResponse toDto(ShortUrl ent) {
        ShortUrlResponse r = new ShortUrlResponse();
        r.setId(ent.getId());
        r.setUrl(ent.getUrl());
        r.setShortCode(ent.getShortCode());
        r.setCreatedAt(ent.getCreatedAt());
        r.setUpdatedAt(ent.getUpdatedAt());
        r.setAccessCount(ent.getAccessCount());
        return r;
    }
}
