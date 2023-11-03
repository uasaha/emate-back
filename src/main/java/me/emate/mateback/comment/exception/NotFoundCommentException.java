package me.emate.mateback.comment.exception;

public class NotFoundCommentException extends RuntimeException {
    private static final String MESSAGE = "Has No Mom";

    public NotFoundCommentException() {
        super(MESSAGE);
    }
}
