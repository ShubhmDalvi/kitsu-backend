package com.example.urlshortener.controller;

import com.example.urlshortener.dto.CreateShortUrlRequest;
import com.example.urlshortener.dto.ShortUrlResponse;
import com.example.urlshortener.dto.UpdateShortUrlRequest;
import com.example.urlshortener.exception.ApiError;
import com.example.urlshortener.exception.NotFoundException;
import com.example.urlshortener.service.ShortUrlService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shorten")
@CrossOrigin(origins = "${cors.allowed.origins:*}")
public class ShortenController {

    private final ShortUrlService service;

    @Value("${server.port}")
    private int serverPort;

    public ShortenController(ShortUrlService service) {
        this.service = service;
    }

    // Create
    @PostMapping
    public ResponseEntity<ShortUrlResponse> create(@Valid @RequestBody CreateShortUrlRequest req) {
        ShortUrlResponse res = service.create(req.getUrl());
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    // Retrieve metadata
    @GetMapping("/{shortCode}")
    public ResponseEntity<ShortUrlResponse> get(@PathVariable String shortCode) {
        ShortUrlResponse res = service.getByShortCode(shortCode);
        return ResponseEntity.ok(res);
    }

    // Update
    @PutMapping("/{shortCode}")
    public ResponseEntity<ShortUrlResponse> update(@PathVariable String shortCode,
                                                   @Valid @RequestBody UpdateShortUrlRequest req) {
        ShortUrlResponse res = service.update(shortCode, req.getUrl());
        return ResponseEntity.ok(res);
    }

    // Delete
    @DeleteMapping("/{shortCode}")
    public ResponseEntity<Void> delete(@PathVariable String shortCode) {
        service.delete(shortCode);
        return ResponseEntity.noContent().build();
    }

    // Stats: same as get but returns access count included (dto already contains it)
    @GetMapping("/{shortCode}/stats")
    public ResponseEntity<ShortUrlResponse> stats(@PathVariable String shortCode) {
        ShortUrlResponse res = service.getByShortCode(shortCode);
        return ResponseEntity.ok(res);
    }

    // Redirect endpoint: increments accessCount and issues 301
    @GetMapping("/r/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        ShortUrlResponse updated = service.incrementAndGet(shortCode);
        URI target = URI.create(updated.getUrl());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(target);
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).headers(headers).build();
    }

    /*
      Basic exception handlers (returns readable JSON). In a larger project move to @ControllerAdvice class.
     */

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(NotFoundException ex) {
        ApiError error = new ApiError(ex.getMessage(), List.of());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex) {
        List<String> details = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.toList());
        ApiError err = new ApiError("Validation failed", details);
        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleBadRequest(IllegalArgumentException ex) {
        ApiError err = new ApiError(ex.getMessage(), List.of());
        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleUnknown(Exception ex) {
        ApiError err = new ApiError("Internal error: " + ex.getMessage(), List.of());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }
}
