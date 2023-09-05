package me.emate.mateback.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoryListResponseDto {
    private Integer categoryNo;
    private String categoryName;
    private Long contentsCnt;
}
