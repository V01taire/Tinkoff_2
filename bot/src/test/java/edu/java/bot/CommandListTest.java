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

public class CommandListTest {

    @Test
    public void listCommandTest() {
        MsgHandler handler = new MsgHandler();
        ReflectionTestUtils.setField(
            handler,
            "commands",
            List.of(
                new CommandStart(),
                new CommandHelp(),
                new CommandTrack(),
                new CommandUntrack(),
                new CommandList()
            )
        );
        Update update = Mockito.mock(Update.class);
        Message message = Mockito.mock(Message.class);
        Chat chat = Mockito.mock(Chat.class);
        Mockito.when(update.message()).thenReturn(message);
        Mockito.when(message.chat()).thenReturn(chat);
        Mockito.when(chat.id()).thenReturn(1L);
        Mockito.when(message.text()).thenReturn("/list");

        String result = handler.handleCommand(update).getParameters().get("text").toString();
        String expected = "🔍 The list of tracked links is empty";

        assertThat(result)
            .isEqualTo(expected);
    }
}
