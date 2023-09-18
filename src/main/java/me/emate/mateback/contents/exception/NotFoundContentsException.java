package me.emate.mateback.contents.exception;

public class NotFoundContentsException extends RuntimeException {
    private static String MESSAGE = "찾을 수 없는 글입니다.";

    public NotFoundContentsException() {
        super(MESSAGE);
    }
}
