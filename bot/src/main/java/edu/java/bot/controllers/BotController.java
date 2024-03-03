package edu.java.bot.controllers;

import edu.java.bot.dtos.LinkUpdateDto;
import edu.java.bot.exceptions.EmptyChatIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BotController {

    @PostMapping("/updates")
    public ResponseEntity<Void> updateLinks(@RequestBody LinkUpdateDto linkUpdateDto) {
        if (linkUpdateDto.tgChatIds().isEmpty()) {
            throw new EmptyChatIdException("Chat id must be non empty.");
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
