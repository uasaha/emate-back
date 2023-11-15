package me.emate.mateback.tag.exception;

/**
 * Not found tag일 때 던지는 exception.
 */
public class NotFoundTagException extends RuntimeException {

  private static final String MESSAGE = "NOT FOUND TAG";

  public NotFoundTagException() {
    super(MESSAGE);
  }
}
