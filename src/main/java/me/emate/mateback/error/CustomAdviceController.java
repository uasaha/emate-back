package me.emate.mateback.error;

import lombok.extern.slf4j.Slf4j;
import me.emate.mateback.category.exception.CategoryNotFoundException;
import me.emate.mateback.contents.exception.NotFoundContentsException;
import me.emate.mateback.member.exception.MemberLoginException;
import me.emate.mateback.member.exception.MemberParseException;
import me.emate.mateback.member.exception.NotFoundMemberException;
import me.emate.mateback.tag.exception.NotFoundTagException;
import me.emate.mateback.tag.exception.TagNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomAdviceController {
    @ExceptionHandler({CategoryNotFoundException.class, NotFoundTagException.class,
            NotFoundContentsException.class, NotFoundTagException.class, TagNotFoundException.class})
    public ResponseEntity<Void> notFound() {
        log.warn("Not founds");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .build();
    }

    @ExceptionHandler({MemberLoginException.class, MemberParseException.class,
            NotFoundMemberException.class})
    public ResponseEntity<Void> unauthorized() {
        log.warn("Unauthorized");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Void> error() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .build();
    }
}
