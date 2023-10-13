package me.emate.mateback.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@NoArgsConstructor
public class PageableResponse<T> {
    private List<T> contents;
    private int totalPages;
    private int current;
    private boolean hasPrevious;
    private boolean hasNext;

    public PageableResponse(Page<T> result) {
        this.contents = result.getContent();
        this.totalPages = result.getTotalPages();
        this.current = result.getNumber();
        this.hasPrevious = result.hasPrevious();
        this.hasNext = result.hasNext();
    }
}
