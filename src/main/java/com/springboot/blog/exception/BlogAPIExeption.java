package com.springboot.blog.exception;

import org.springframework.http.HttpStatus;

public class BlogAPIExeption extends RuntimeException{

    private HttpStatus status;
    private String message;

    public BlogAPIExeption(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public BlogAPIExeption(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
