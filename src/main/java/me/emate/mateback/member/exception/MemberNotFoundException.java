package me.emate.mateback.member.exception;

/**
 * Member 찾지 못할 때 던지는 exception입니다.
 *
 * @author 여운석
 */
public class MemberNotFoundException extends RuntimeException {

  private static final String MESSAGE = "멤버를 찾을 수 없습니다.";

  /**
   * Instantiates a new Member not found exception.
   */
  public MemberNotFoundException() {
    super(MESSAGE);
  }
}
