package me.emate.mateback.member.dto;

public class MemberNotFoundException extends RuntimeException {
    private final static String MESSAGE = "멤버를 찾을 수 없습니다.";

    public MemberNotFoundException() {
        super(MESSAGE);
    }
}
