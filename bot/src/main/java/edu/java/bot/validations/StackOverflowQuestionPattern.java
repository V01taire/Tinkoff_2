package edu.java.bot.validations;

import java.util.regex.Pattern;

public class StackOverflowQuestionPattern extends Validator {
    public StackOverflowQuestionPattern(Validator nextValidator) {
        super(nextValidator);
    }

    @Override
    protected Pattern getPatter() {
        return Pattern.compile("https://stackoverflow.com/questions/\\d+");
    }
}
