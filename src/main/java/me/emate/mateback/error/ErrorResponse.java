package me.emate.mateback.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 공통된 Error를 보내기 위한 response.
 *
 * @author 여운석
 */
@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private String code;
}
