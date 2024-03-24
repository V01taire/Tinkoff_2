package edu.java.service;

import edu.java.dto.request.LinkRequestDto;
import edu.java.dto.response.LinkResponseDto;
import edu.java.dto.response.ListLinkResponseDto;
import edu.java.exception.exception.LinkAlreadyTrackedException;
import edu.java.exception.exception.UserHasNoLinkException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;

public interface LinkService {

    LinkResponseDto create(Long chatId, LinkRequestDto body);

    LinkResponseDto delete(Long chatId, LinkRequestDto body);

    ListLinkResponseDto getAll(Long chatId);
}
