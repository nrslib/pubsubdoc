package com.pubsubdoc.user.service.web.http.controllers.advice;

import com.example.appkit.http.exceptions.NotFoundException;
import com.pubsubdoc.user.service.web.http.models.common.ErrorsResponse;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Hidden
@RestControllerAdvice
public class ExpectedExceptionAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsResponse handle(NotFoundException notFoundException) {
        String code = notFoundException.getErrorCode();
        if (code == null) {
            code = "404";
        }

        String message = notFoundException.getMessage();
        if (message == null) {
            message = "Not Found";
        }

        return ErrorsResponse.apply(code, message);
    }
}
