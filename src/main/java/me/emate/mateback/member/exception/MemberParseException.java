package me.emate.mateback.member.exception;

public class MemberParseException extends RuntimeException {
    private static final String MESSAGE = "멤버 파싱 예외입니다.";

    public MemberParseException() {
        super(MESSAGE);
    }
}
