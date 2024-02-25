package edu.java.bot;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import edu.java.bot.commands.CommandHelp;
import edu.java.bot.commands.CommandList;
import edu.java.bot.commands.CommandStart;
import edu.java.bot.commands.CommandTrack;
import edu.java.bot.commands.CommandUntrack;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import static org.assertj.core.api.Assertions.assertThat;

public class CommandHelpTest {

    @Test
    public void helpCommandTest() {
        CommandHelp command = new CommandHelp();
        ReflectionTestUtils.setField(
            command,
            "availableCommands",
            List.of(
                new CommandList(),
                new CommandStart(),
                new CommandTrack(),
                new CommandUntrack(),
                new CommandHelp()
            )
        );
        Update update = Mockito.mock(Update.class);
        Message message = Mockito.mock(Message.class);
        Chat chat = Mockito.mock(Chat.class);
        Mockito.when(update.message()).thenReturn(message);
        Mockito.when(message.chat()).thenReturn(chat);
        Mockito.when(chat.id()).thenReturn(1L);
        Mockito.when(message.text()).thenReturn("/help");

        String result = command.handle(update).getParameters().get("text").toString();
        String expected = "/list\n" +
            "🔍 Command that displays a list of all tracked links.\n\n" +
            "/start\n" +
            "🚀 Command to register a user in the bot.\n\n" +
            "/track\n" +
            "📌 Command to add a link for tracking.\n" +
            "(/track link [name]). Name is an optional parameter.\n\n" +
            "/untrack\n" +
            "❌ Command to remove a tracked link.\n" +
            "(/untrack link | name). Use either the link or its name.\n\n" +
            "/help\n" +
            "ℹ️ Command that displays a list of all possible commands.";

        assertThat(result)
            .isEqualTo(expected);
    }
}
