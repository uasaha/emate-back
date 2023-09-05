package me.emate.mateback.token.exception;

public class TokenUnusualApproachException extends RuntimeException {
    public static final String MESSAGE = "비정상적인 접근 입니다.";

    public TokenUnusualApproachException() {
        super(MESSAGE);
    }
}
