package me.emate.mateback.member.exception;

/**
 * Member login시 던지는 exception입니다.
 *
 * @author 여운석
 */
public class MemberLoginException extends RuntimeException {
    private static final String MESSAGE = "로그인 예외입니다.";

    /**
     * Instantiates a new Member login exception.
     */
    public MemberLoginException() {
        super(MESSAGE);
    }
}
