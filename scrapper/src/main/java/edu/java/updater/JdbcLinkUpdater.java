package edu.java.updater;

import edu.java.configuration.ApplicationConfig;
import edu.java.database.model.Chat;
import edu.java.database.model.ChatToLink;
import edu.java.database.model.Link;
import edu.java.database.repository.ChatToLinkRepository;
import edu.java.database.repository.LinkRepository;
import edu.java.dto.request.BotLinkUpdateRequestDto;
import edu.java.processor.LinkProcessor;
import edu.java.service.BotService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcLinkUpdater implements LinkUpdater {

    LinkRepository linkRepository;
    ChatToLinkRepository chatToLinkRepository;
    ApplicationConfig applicationConfig;
    LinkProcessor linkProcessor;
    BotService botService;

    public JdbcLinkUpdater(
        JdbcLinkRepository jdbcLinkRepository,
        JdbcChatToLinkRepository jdbcChatToLinkRepository,
        ApplicationConfig applicationConfig,
        LinkProcessor linkProcessor,
        BotService botService
    ) {
        this.jdbcLinkRepository = jdbcLinkRepository;
        this.jdbcChatToLinkRepository = jdbcChatToLinkRepository;
        this.applicationConfig = applicationConfig;
        this.linkProcessor = linkProcessor;
        this.botService = botService;
    }

    @Override
    public void update() {
        List<Link> links = linkRepository
            .findByTime(applicationConfig.scheduler().forceCheckDelay());

        for (Link link : links) {

            Map<Long, String> info = new HashMap<>();

            linkProcessor.processLink(link.getLinkUrl(), link.getLastCheckTime())
                .doOnSuccess(e -> linkRepository.updateLastCheck(link.getLinkUrl()))
                .subscribe(e -> {
                        for (ChatToLink chatToLink : chatToLinkRepository.findAllChatByLink(link.getId())) {
                            Chat chat = chatToLink.getChat();
                            info.put(chat.getId(), chatToLink.getName());
                        }
                        botService.sendLinkUpdate(new BotLinkUpdateRequestDto(
                                link.getId(),
                                e.link(),
                                e.description(),
                                info
                            ))
                            .subscribe();
                    }
                );
        }
    }
}
