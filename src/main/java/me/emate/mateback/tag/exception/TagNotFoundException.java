package me.emate.mateback.tag.exception;

public class TagNotFoundException extends RuntimeException {
    private static final String MESSAGE = "NOT FOUND TAG";

    public TagNotFoundException() {
        super(MESSAGE);
    }
}
