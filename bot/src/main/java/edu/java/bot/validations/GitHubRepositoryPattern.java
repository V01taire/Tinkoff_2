package edu.java.bot.validations;

import java.util.regex.Pattern;

public class GitHubRepositoryPattern extends Validator {

    public GitHubRepositoryPattern(Validator nextValidator) {
        super(nextValidator);
    }

    @Override
    protected Pattern getPatter() {
        return Pattern.compile("https://github.com/[\\w+|-]+/[\\w+|-]+");
    }
}
