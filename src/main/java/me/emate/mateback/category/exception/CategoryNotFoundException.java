package me.emate.mateback.category.exception;

/**
 * 카테고리를 찾을 수 없을 때 던지는 예외입니다.
 *
 * @author 여운석
 */
public class CategoryNotFoundException extends RuntimeException {
    private final static String MESSAGE = "NOT FOUND CATEGORY";

    public CategoryNotFoundException() {
        super(MESSAGE);
    }
}
