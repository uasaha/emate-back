package me.emate.mateback.member.exception;

/**
 * Member를 파싱할 때 발생하는 오류에 던지는 exception입니다.
 *
 * @author 여운석
 */
public class MemberParseException extends RuntimeException {

  private static final String MESSAGE = "멤버 파싱 예외입니다.";

  /**
   * Instantiates a new Member parse exception.
   */
  public MemberParseException() {
    super(MESSAGE);
  }
}
