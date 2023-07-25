package me.emate.mateback.tag.controller;

import lombok.RequiredArgsConstructor;
import me.emate.mateback.tag.dto.TagListResponseDto;
import me.emate.mateback.tag.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tag")
public class TagController {
    private final TagService tagService;

    @GetMapping
    public ResponseEntity<List<TagListResponseDto>> findAllTags() {
        return ResponseEntity.ok().body(tagService.findAllTags());
    }

    @PostMapping
    public ResponseEntity<Void> createTag(@RequestBody String name) {
        tagService.createTag(name);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/del")
    public ResponseEntity<Void> deleteTag(@RequestParam Integer tagNo) {
        tagService.deleteTag(tagNo);
        return ResponseEntity.ok().build();
    }

}
