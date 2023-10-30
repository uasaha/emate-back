package me.emate.mateback.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 공통된 페이징 처리를 위한 Pageable response dto 입니다.
 *
 * @param <T> the type parameter
 */
@Getter
@NoArgsConstructor
public class PageableResponse<T> {
    private List<T> contents;
    private int totalPages;
    private int current;
    private boolean hasPrevious;
    private boolean hasNext;

    /**
     * Instantiates a new Pageable response.
     *
     * @param result the result
     */
    public PageableResponse(Page<T> result) {
        this.contents = result.getContent();
        this.totalPages = result.getTotalPages();
        this.current = result.getNumber();
        this.hasPrevious = result.hasPrevious();
        this.hasNext = result.hasNext();
    }
}
