package com.example.google_ads_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoIDSpecifiedException extends RuntimeException {
    public NoIDSpecifiedException(String message) {
        super(message);
    }
}
