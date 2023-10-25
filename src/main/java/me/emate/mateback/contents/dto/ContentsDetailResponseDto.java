package me.emate.mateback.contents.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.emate.mateback.tag.dto.TagListResponseDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ContentsDetailResponseDto {
    private Integer contentsNo;
    private String category;
    @Setter
    private List<TagListResponseDto> tags;
    private boolean isDeleted;
    private boolean isHidden;
    private String subject;
    private String detail;
    private Integer views;
    private Integer loving;
    private LocalDateTime createdAt;
    private String thumbnail;

    public ContentsDetailResponseDto(Integer contentsNo, String category, boolean isDeleted, boolean isHidden, String subject, String detail, Integer views, Integer loving, LocalDateTime createdAt, String thumbnail) {
        this.contentsNo = contentsNo;
        this.category = category;
        this.isDeleted = isDeleted;
        this.isHidden = isHidden;
        this.subject = subject;
        this.detail = detail;
        this.views = views;
        this.loving = loving;
        this.createdAt = createdAt;
        this.thumbnail = thumbnail;
    }
}
