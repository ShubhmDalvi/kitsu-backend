package com.example.urlshortener.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateShortUrlRequest {

    @NotBlank(message = "url is required")
    @Size(max = 4096, message = "url too long")
    private String url;

    public UpdateShortUrlRequest() {}
    public UpdateShortUrlRequest(String url) { this.url = url; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}
