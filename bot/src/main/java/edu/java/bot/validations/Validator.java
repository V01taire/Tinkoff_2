package edu.java.bot.validations;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Validator {

    private static final Logger LOGGER = LogManager.getLogger();
    protected Validator nextValidator;

    public Validator(Validator nextValidator) {
        this.nextValidator = nextValidator;
    }

    protected abstract Pattern getPatter();

    public URI validate(String url) {
        Matcher matcher = getPatter().matcher(url);
        if (matcher.find()) {
            try {
                return new URI(url);
            } catch (URISyntaxException e) {
                LOGGER.error(e.getMessage());
            }
        } else if (nextValidator != null) {
            return nextValidator.validate(url);
        }
        return null;
    }
}
