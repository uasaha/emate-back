package me.emate.mateback.contents.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ContentsDetailResponseDto {
    private Integer contentsNo;
    private String category;
    private List<String> tags;
    private boolean isDeleted;
    private boolean isHidden;
    private String subject;
    private String detail;
    private Integer views;
    private Integer loving;
    private LocalDateTime createdAt;
}
