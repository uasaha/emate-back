package me.emate.mateback.contents.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.mateback.contents.dto.ContentsDetailResponseDto;
import me.emate.mateback.contents.dto.CreateContentsRequestDto;
import me.emate.mateback.contents.service.ContentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/content")
public class ContentsController {
    private final ContentsService contentsService;

    @PostMapping("/register")
    public ResponseEntity<ContentsDetailResponseDto> createContents(
            @RequestBody CreateContentsRequestDto requestDto) {
        ContentsDetailResponseDto responseDto = contentsService.createContents(requestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }
}
