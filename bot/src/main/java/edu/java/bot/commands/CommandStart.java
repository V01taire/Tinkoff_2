package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class CommandStart implements Command {

    @SuppressWarnings("LineLength")
    private static final String HELLO_MESSAGE = "👋 Hello, I'm a bot for tracking changes in links that you provide to me. "
        + "You can learn about my capabilities by using the command /help.";
    private static final String COMMAND = "/start";
    private static final String COMMAND_DESCRIPTION = "🚀 Command to register a user in the bot.";

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
            HELLO_MESSAGE);
    }
}
