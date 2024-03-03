package edu.java.controllers;

import edu.java.dtos.ListLinkResponseDto;
import edu.java.dtos.ListLinkResponseDto.LinkResponseDto;
import edu.java.exceptions.userexceptions.LinkAlreadyTrackedException;
import edu.java.exceptions.userexceptions.UserWithNoLinkException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/links")
public class LinkController {

    Map<Long, List<LinkResponseDto>> linkStub = new HashMap<>();

    @GetMapping
    public ResponseEntity<ListLinkResponseDto> getAllLinks(@RequestHeader("id") Long id) {
        if (!linkStub.containsKey(id)) {
            throw new UserWithNoLinkException("Links are empty.");
        }
        List<LinkResponseDto> linkResponsDtos = linkStub.get(id);
        return new ResponseEntity<>(new ListLinkResponseDto(linkResponsDtos, linkResponsDtos.size()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LinkResponseDto> addLink(@RequestHeader("id") Long id, @RequestBody LinkResponseDto body) {
        if (!linkStub.containsKey(id)) {
            linkStub.put(id, new ArrayList<>());
        }
        Optional<LinkResponseDto> findLinkInArray =
            linkStub.get(id).stream().filter(e -> e.equals(body.url())).findFirst();
        if (findLinkInArray.isPresent()) {
            throw new LinkAlreadyTrackedException("Link already tracked");
        }
        LinkResponseDto linkResponseDto = new LinkResponseDto(id, body.url());
        linkStub.get(id).add(linkResponseDto);
        return new ResponseEntity<>(linkResponseDto, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<LinkResponseDto> deleteLink(@RequestHeader("id") Long id, @RequestBody LinkResponseDto body) {
        if (!linkStub.containsKey(id)) {
            throw new UserWithNoLinkException("User does not have any link.");
        }
        List<LinkResponseDto> linkList = linkStub.get(id);
        LinkResponseDto linkResponseDto =
            linkList.stream().filter(e -> e.url().equals(body.url())).findFirst().orElse(null);
        if (linkResponseDto == null) {
            throw new UserWithNoLinkException("User does not have this link.");
        }
        linkList.remove(body.url());
        return new ResponseEntity<>(new LinkResponseDto(id, body.url()), HttpStatus.OK);
    }
}
