package com.pubsubdoc.service.back.http.controllers.advice;

import com.pubsubdoc.service.back.http.models.common.ErrorsResponse;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AxonExceptionAdvice {
    @ExceptionHandler(AggregateNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsResponse handle(AggregateNotFoundException ex) {
        return ErrorsResponse.apply("Not Found", ex.getMessage());
    }
}
