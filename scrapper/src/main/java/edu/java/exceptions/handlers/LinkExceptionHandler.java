package edu.java.exceptions.handlers;

import edu.java.exceptions.ErrorMessage;
import edu.java.exceptions.userexceptions.LinkAlreadyTrackedException;
import edu.java.exceptions.userexceptions.UserWithNoLinkException;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class LinkExceptionHandler {

    @ExceptionHandler(UserWithNoLinkException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage userHasNoLinkExceptionHandler(UserWithNoLinkException ex, WebRequest request) {
        return new ErrorMessage(
            "Can`t find user links.",
            String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()),
            ex.getClass().getName(),
            ex.getMessage(),
            Arrays.stream(ex.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.toList())
        );
    }

    @ExceptionHandler(LinkAlreadyTrackedException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorMessage linkAlreadyTrackedExceptionHandler(UserWithNoLinkException ex, WebRequest request) {
        return new ErrorMessage(
            "Can`t add link.",
            String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()),
            ex.getClass().getName(),
            ex.getMessage(),
            Arrays.stream(ex.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.toList())
        );
    }
}
