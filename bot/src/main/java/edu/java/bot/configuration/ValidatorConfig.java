package edu.java.bot.configuration;

import edu.java.bot.validations.GitHubRepositoryPattern;
import edu.java.bot.validations.StackOverflowQuestionPattern;
import edu.java.bot.validations.StackOverflowSearchPattern;
import edu.java.bot.validations.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorConfig {

    @Bean
    public Validator linkValidator() {
        StackOverflowSearchPattern stackOverflowSearchPattern = new StackOverflowSearchPattern(null);
        StackOverflowQuestionPattern stackOverflowQuestionPattern =
            new StackOverflowQuestionPattern(stackOverflowSearchPattern);
        GitHubRepositoryPattern gitHubRepositoryPattern = new GitHubRepositoryPattern(stackOverflowQuestionPattern);

        return gitHubRepositoryPattern;
    }
}
