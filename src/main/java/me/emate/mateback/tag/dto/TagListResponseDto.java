package me.emate.mateback.tag.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TagListResponseDto {
    private Integer tagNo;
    private String tagName;
    private String tagColor;

    public TagListResponseDto(Integer tagNo, String tagName) {
        this.tagNo = tagNo;
        this.tagName = tagName;
    }
}
