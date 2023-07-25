package me.emate.mateback.category.exception;

public class CategoryNotFoundException extends RuntimeException {
    private final static String MESSAGE = "NOT FOUND CATEGORY";

    public CategoryNotFoundException() {
        super(MESSAGE);
    }
}
