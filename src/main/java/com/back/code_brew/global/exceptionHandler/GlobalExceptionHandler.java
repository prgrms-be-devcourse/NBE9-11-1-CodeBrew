package com.back.code_brew.global.exceptionHandler;

import com.back.code_brew.global.rsData.RsData;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public RsData<Void> handleException(NoSuchElementException e) {
        return new RsData<>(
                e.getMessage(),
                "404-1"
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public RsData<Void> handleException(IllegalArgumentException e) {
        return new RsData<>(
                e.getMessage(),
                "400-1"
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RsData<Void> handleException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult()
                .getAllErrors()
                .stream()
                .filter(error -> error instanceof FieldError)
                .map(error -> (FieldError) error)
                .map(error -> error.getField() + "-" + error.getCode() + "-" + error.getDefaultMessage())
                .sorted(Comparator.comparing(String::toString))
                .collect(Collectors.joining("\n"));

        return new RsData<>(
                message,
                "400-2"
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public RsData<Void> handleException(HttpMessageNotReadableException e) {
        return new RsData<>(
                "잘못된 형식의 요청 데이터입니다.",
                "400-3"
        );
    }

    @ExceptionHandler(Exception.class)
    public RsData<Void> handleException(Exception e) {
        return new RsData<>(
                "서버 내부 오류가 발생했습니다.",
                "500-1"
        );
    }
}