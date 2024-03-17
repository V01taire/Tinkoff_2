package edu.java.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import java.util.List;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class MessageProcessor implements UpdatesListener {

    private TelegramBot telegramBot;
    private MessageHandler messageHandler;

    public MessageProcessor(TelegramBot telegramBot, MessageHandler messageHandler) {
        telegramBot.setUpdatesListener(this);

        this.telegramBot = telegramBot;
        this.messageHandler = messageHandler;
    }

    @Override
    public int process(List<Update> list) {
        Flux.fromIterable(list)
            .subscribe(update -> {
                if (update != null) {
                    if (update.message() != null) {
                        messageHandler.handleCommand(update).subscribe(msg -> telegramBot.execute(msg));
                    }
                }
            });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
