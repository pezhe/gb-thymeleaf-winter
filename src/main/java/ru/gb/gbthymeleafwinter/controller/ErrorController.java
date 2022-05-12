package ru.gb.gbthymeleafwinter.controller;

import feign.FeignException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(FeignException.Forbidden.class)
    public String handleError() {
        return "/access-denied";
    }

}
