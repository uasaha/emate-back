package me.emate.mateback.token.exception;

/**
 * 사용할 수 없는 토큰에 대해 던지는 exception입니다.
 *
 * @author 여운석
 */
public class TokenUnusualApproachException extends RuntimeException {
    /**
     * The constant MESSAGE.
     */
    public static final String MESSAGE = "비정상적인 접근 입니다.";

    /**
     * Instantiates a new Token unusual approach exception.
     */
    public TokenUnusualApproachException() {
        super(MESSAGE);
    }
}
