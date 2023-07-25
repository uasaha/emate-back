package me.emate.mateback.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoryListResponseDto {
    private Integer categoryNo;
    private String categoryName;

    @Setter
    private Long contentsCnt;

    public CategoryListResponseDto(Integer categoryNo, String  categoryName) {
        this.categoryNo = categoryNo;
        this.categoryName = categoryName;
    }
}
