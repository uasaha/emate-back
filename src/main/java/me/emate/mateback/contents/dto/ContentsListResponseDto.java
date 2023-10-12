package me.emate.mateback.contents.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContentsListResponseDto {
    private String thumbnail;
    private String subject;
    private LocalDateTime createdAt;
    private int loving;
}
