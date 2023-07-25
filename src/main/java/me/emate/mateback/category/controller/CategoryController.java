package me.emate.mateback.category.controller;

import lombok.RequiredArgsConstructor;
import me.emate.mateback.category.dto.CategoryListResponseDto;
import me.emate.mateback.category.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryListResponseDto>> findAllCategories() {
        return ResponseEntity.ok().body(categoryService.findAllCategories());
    }

    @PostMapping
    public ResponseEntity<Void> createCategory(@RequestBody String name) {
        categoryService.createCategory(name);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/del")
    public ResponseEntity<Void> deleteCategory(@RequestParam String name) {
        categoryService.deleteCategory(name);
        return ResponseEntity.ok().build();
    }
}
