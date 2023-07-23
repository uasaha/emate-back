package me.emate.mateback.member.exception;

public class MemberLoginException extends RuntimeException {
    private static String MESSAGE = "로그인 예외입니다.";

    public MemberLoginException() {
        super(MESSAGE);
    }
}
