package com.example.urlshortener.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateShortUrlRequest {

    @NotBlank(message = "url is required")
    @Size(max = 4096, message = "url too long")
    private String url;

    public CreateShortUrlRequest() {}
    public CreateShortUrlRequest(String url) { this.url = url; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}
