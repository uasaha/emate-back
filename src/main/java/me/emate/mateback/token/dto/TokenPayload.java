package me.emate.mateback.token.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Token payload를 전달하는 dto입니다.
 *
 * @author 여운석
 */
@Getter
@AllArgsConstructor
public class TokenPayload {

  private String memberUUID;
  private String roles;
}
