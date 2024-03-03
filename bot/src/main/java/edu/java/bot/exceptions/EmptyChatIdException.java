package edu.java.bot.exceptions;

public class EmptyChatIdException extends RuntimeException {
    public EmptyChatIdException(String message) {
        super(message);
    }
}
