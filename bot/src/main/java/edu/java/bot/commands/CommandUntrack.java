package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class CommandUntrack implements Command {

    private static final String INCORRECT_LENGTH = "❗ The command must be in the format '/untrack link | name'.";
    private static final String COMMAND = "/untrack";
    private static final String COMMAND_DESCRIPTION = "🔍 Command to remove a tracked link.\n"
        + "(/untrack link | name). Use either the link or its name.";

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
            getLink(update)
        );
    }

    private String getLink(Update update) {
        String[] msg = update.message().text().split(" ");
        if (msg.length < 2) {
            return INCORRECT_LENGTH;
        }
        String link = msg[1];
        return link + " была удалена.";
    }
}
