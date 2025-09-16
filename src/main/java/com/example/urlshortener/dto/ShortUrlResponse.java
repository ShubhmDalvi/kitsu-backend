package com.example.urlshortener.dto;

import java.time.OffsetDateTime;

public class ShortUrlResponse {
    private Long id;
    private String url;
    private String shortCode;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private long accessCount;

    // constructors, getters, setters

    public ShortUrlResponse() {}

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
}
