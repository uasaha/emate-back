package me.emate.mateback.contents.exception;

/**
 * 콘텐츠를 찾을 수 없을 때 던지는 exception.
 *
 * @author 여운석
 */
public class NotFoundContentsException extends RuntimeException {
    private static final String MESSAGE = "찾을 수 없는 글입니다.";

    /**
     * 생성자.
     */
    public NotFoundContentsException() {
        super(MESSAGE);
    }
}
