package me.emate.mateback.member.exception;

public class NotFoundMemberException extends RuntimeException {
    private static String MESSAGE = "찾을 수 없는 회원입니다.";

    public NotFoundMemberException() {
        super(MESSAGE);
    }
}
