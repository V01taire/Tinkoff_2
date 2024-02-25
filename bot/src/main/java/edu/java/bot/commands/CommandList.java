package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CommandList implements Command {

    private static final String EMPTY_LINKS = "The list of tracked links is empty";
    private static final String COMMAND = "/list";
    private static final String COMMAND_DESCRIPTION = "Command that displays a list of all tracked links.";
    private List<String> links = new ArrayList<>();

    @Override
    public String command() {
        return COMMAND;
    }

    @Override
    public String description() {
        return COMMAND_DESCRIPTION;
    }

    @Override
    public SendMessage handle(Update update) {
        return new SendMessage(
            update.message().chat().id(),
            getLinks()
        );
    }

    private String getLinks() {
        return links.isEmpty()
            ? EMPTY_LINKS
            : String.join("\n", links);
    }
}
