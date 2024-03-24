package edu.java.service.jdbc;

import edu.java.database.repository.ChatRepository;
import edu.java.exception.exception.UserAlreadyRegisteredException;
import edu.java.exception.exception.UserNotRegisteredException;
import edu.java.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;

public class JdbcChatService implements ChatService {

    ChatRepository chatRepository;

    public JdbcChatService(JdbcChatRepository jdbcChatRepository) {
        this.jdbcChatRepository = jdbcChatRepository;
    }

    @Override
    public void register(Long chatId) {
        if (chatRepository.add(chatId) == null) {
            throw new UserAlreadyRegisteredException("Can`t register user.");
        }
    }

    @Override
    public void unregister(Long chatId) {
        if (chatRepository.remove(chatId) == null) {
            throw new UserNotRegisteredException("Can`t delete user.");
        }
    }


}
