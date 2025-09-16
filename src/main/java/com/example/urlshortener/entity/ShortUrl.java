package com.example.urlshortener.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "short_urls", indexes = {
        @Index(columnList = "shortCode", name = "idx_short_code", unique = true)
})
public class ShortUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 4096)
    private String url;

    @Column(nullable = false, unique = true, length = 64)
    private String shortCode;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    private long accessCount = 0L;

    public ShortUrl() {}

    // getters and setters (or use Lombok)
    // I'll include the essential ones:

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getShortCode() { return shortCode; }
    public void setShortCode(String shortCode) { this.shortCode = shortCode; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }

    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }

    public long getAccessCount() { return accessCount; }
    public void setAccessCount(long accessCount) { this.accessCount = accessCount; }

    public void incrementAccessCount() { this.accessCount++; }
}
