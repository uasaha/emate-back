package me.emate.mateback.comment.exception;

/**
 * The type Not found comment exception.
 */
public class NotFoundCommentException extends RuntimeException {

  private static final String MESSAGE = "Has No Mom";

  /**
   * Instantiates a new Not found comment exception.
   */
  public NotFoundCommentException() {
    super(MESSAGE);
  }
}
