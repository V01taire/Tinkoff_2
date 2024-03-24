package edu.java.service;

import edu.java.exception.exception.UserAlreadyRegisteredException;
import edu.java.exception.exception.UserNotRegisteredException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

public interface ChatService {
    void register(Long chatId);

    void unregister(Long chatId);

}
