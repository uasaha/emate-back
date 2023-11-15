package me.emate.mateback.error;

import lombok.extern.slf4j.Slf4j;
import me.emate.mateback.category.exception.CategoryNotFoundException;
import me.emate.mateback.contents.exception.NotFoundContentsException;
import me.emate.mateback.member.exception.MemberLoginException;
import me.emate.mateback.member.exception.MemberNotFoundException;
import me.emate.mateback.member.exception.MemberParseException;
import me.emate.mateback.tag.exception.NotFoundTagException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 예외를 처리하기 위한 클래스입니다.
 *
 * @author 여운석
 */
@Slf4j
@RestControllerAdvice
public class CustomAdviceController {

  /**
   * Not found response entity.
   *
   * @return the response entity
   */
  @ExceptionHandler({CategoryNotFoundException.class, NotFoundTagException.class,
      NotFoundContentsException.class, NotFoundTagException.class})
  public ResponseEntity<Void> notFound() {
    log.warn("Not founds");
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .build();
  }

  /**
   * Unauthorized response entity.
   *
   * @return the response entity
   */
  @ExceptionHandler({MemberLoginException.class, MemberParseException.class,
      MemberNotFoundException.class})
  public ResponseEntity<Void> unauthorized() {
    log.warn("Unauthorized");
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }

  /**
   * Error response entity.
   *
   * @return the response entity
   */
  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<Void> error() {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .build();
  }
}
