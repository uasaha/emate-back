package me.emate.mateback.category.controller;

import lombok.RequiredArgsConstructor;
import me.emate.mateback.category.dto.CategoryListResponseDto;
import me.emate.mateback.category.service.CategoryService;
import me.emate.mateback.contents.dto.ContentsListResponseDto;
import me.emate.mateback.contents.service.ContentsService;
import me.emate.mateback.utils.PageableResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 카테고리의 컨트롤러입니다.
 *
 * @author 여운석
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final ContentsService contentsService;

    /**
     * 모든 카테고리를 반환합니다.
     *
     * @return 번호, 이름, 글 갯수가 들어있는 dto의 list
     */
    @GetMapping
    public ResponseEntity<List<CategoryListResponseDto>> findAllCategories() {
        return ResponseEntity.ok().body(categoryService.findAllCategories());
    }

    /**
     * 카레고리를 생성합니다.
     *
     * @param name 카테고리 명
     * @return 성공시 201
     */
    @PostMapping
    public ResponseEntity<Void> createCategory(@RequestBody String name) {
        categoryService.createCategory(name);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 카테고리를 삭제합니다.
     *
     * @param name 카테고리 명
     * @return 200
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteCategory(@RequestParam String name) {
        categoryService.deleteCategory(name);
        return ResponseEntity.ok().build();
    }

    /**
     * 카레고리 이름으로 모든 글을 조회합니다.
     *
     * @param categoryName 카테고리 명
     * @param pageable     페이징
     * @return 콘텐츠 dto의 list
     */
    @GetMapping("{categoryName}")
    public ResponseEntity<PageableResponse<ContentsListResponseDto>> findContentsByCategoryAndPageable(
            @PathVariable String categoryName, Pageable pageable) {
        return ResponseEntity.ok()
                .body(contentsService.getContentsByCategoryAndPageable(categoryName, pageable));
    }
}
