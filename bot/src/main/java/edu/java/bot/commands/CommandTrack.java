package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class CommandTrack implements Command {

    private static final String INCORRECT_LENGTH = "❗ The command must be in the format '/track link [name]'.";
    private static final String COMMAND = "/track";
    private static final String COMMAND_DESCRIPTION = "🔍 Command to add a link for tracking.\n"
        + "(/track link [name]). Name is an optional parameter.";

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

    @SuppressWarnings("MagicNumber")
    private String getLink(Update update) {
        String[] msg = update.message().text().strip().split("\s+", 3);
        if (msg.length < 2) {
            return INCORRECT_LENGTH;
        }
        String link = msg[1];
        return link + " добавлена.";
    }
}
