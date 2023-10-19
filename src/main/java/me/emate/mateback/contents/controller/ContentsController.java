package me.emate.mateback.contents.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.mateback.contents.dto.ContentsDetailResponseDto;
import me.emate.mateback.contents.dto.ContentsListResponseDto;
import me.emate.mateback.contents.dto.CreateContentsRequestDto;
import me.emate.mateback.contents.service.ContentsService;
import me.emate.mateback.utils.PageableResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/contents")
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

    @GetMapping("/{subject}")
    public ResponseEntity<ContentsDetailResponseDto> getContentBySubject(@PathVariable String subject) {
        return  ResponseEntity
                .ok(contentsService.getContentsBySubject(subject));
    }

    @GetMapping("/latest")
    public ResponseEntity<ContentsDetailResponseDto> getLatestContent() {
        return ResponseEntity.ok(contentsService.getLatestContent());
    }

    @GetMapping("/latests")
    public ResponseEntity<List<ContentsListResponseDto>> getLatestContents() {
        return ResponseEntity.ok(contentsService.getLatestContents());
    }

    @GetMapping("/total")
    public ResponseEntity<PageableResponse<ContentsListResponseDto>>
    getTotalContents(@PageableDefault(size = 8)Pageable pageable) {
        return ResponseEntity.ok().body(contentsService.getTotalContents(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<PageableResponse<ContentsListResponseDto>>
    getContentsContainsSearch(@RequestParam("key") String key, @PageableDefault(size = 8) Pageable pageable) {
        return ResponseEntity.ok().body(contentsService.getContentsContainsSearch(key, pageable));
    }
}
