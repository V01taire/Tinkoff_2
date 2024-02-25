package edu.java.configuration;

import edu.java.controllers.GitHubController;
import jakarta.validation.constraints.NotNull;
import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
@ComponentScan(basePackageClasses = GitHubController.class)
@Configuration
public record ApplicationConfig(

    @NotNull
    Scheduler scheduler,

    @NotNull
    String stackoverflowBaseUrl,

    @NotNull
    String githubBaseUrl
) {

    @Bean
    @SuppressWarnings("MagicNumber")
    public Scheduler scheduler() {
        return new Scheduler(true, Duration.ofMinutes(10), Duration.ofHours(1));
    }

    public record Scheduler(boolean enable, @NotNull Duration interval, @NotNull Duration forceCheckDelay) {
    }
}
