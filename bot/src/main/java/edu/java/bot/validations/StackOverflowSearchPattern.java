package edu.java.bot.validations;

import java.util.regex.Pattern;

public class StackOverflowSearchPattern extends Validator {
    public StackOverflowSearchPattern(Validator nextValidator) {
        super(nextValidator);
    }

    @Override
    protected Pattern getPatter() {
        return Pattern.compile("https://stackoverflow.com/search\\?q=[\\w+|+]+");
    }
}
