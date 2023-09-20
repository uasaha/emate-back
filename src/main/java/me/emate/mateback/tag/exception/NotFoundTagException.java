package me.emate.mateback.tag.exception;

public class NotFoundTagException extends RuntimeException {
    private static final String MESSAGE = "NOT FOUND TAG";

    public NotFoundTagException() {
        super(MESSAGE);
    }
}
