package me.emate.mateback.token.exception;

/**
 * 토큰 파싱시 일어나는 오류에 대해 던지는 exception입니다.
 *
 * @author 여운석
 */
public class TokenParseException extends RuntimeException {

  /**
   * The constant MESSAGE.
   */
  public static final String MESSAGE = "토큰을 읽어올 수 없습니다";

  /**
   * Instantiates a new Token parse exception.
   */
  public TokenParseException() {
    super(MESSAGE);
  }
}