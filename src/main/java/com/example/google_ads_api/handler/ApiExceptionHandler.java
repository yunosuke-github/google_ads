package com.example.google_ads_api.handler;

import com.example.google_ads_api.consts.ErrorCode;
import com.example.google_ads_api.dto.ErrorDto;
import com.example.google_ads_api.dto.ResponseDto;
import com.example.google_ads_api.exception.InvalidParamException;
import com.example.google_ads_api.exception.NoIDSpecifiedException;
import com.example.google_ads_api.exception.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        ErrorDto error = new ErrorDto();
        error.setCode(ErrorCode.getCodeByMessage(ex.getMessage()).getCode());
        error.setMessage(ex.getMessage());
        ResponseDto response = new ResponseDto();
        response.setCode(status.value());
        response.setError(error);
        return super.handleExceptionInternal(ex, response, headers, status, request);
    }

    @ExceptionHandler(InvalidParamException.class)
    protected ResponseEntity<Object> invalidParamResponse(InvalidParamException ex, WebRequest request) {
        return handleExceptionInternal(ex, null,null, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> notFoundResponse(NotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, null,null, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(NoIDSpecifiedException.class)
    protected ResponseEntity<Object> noIdSpecifiedResponse(NoIDSpecifiedException ex, WebRequest request) {
        return handleExceptionInternal(ex, null,null, HttpStatus.BAD_REQUEST, request);
    }
}
