package me.emate.mateback.token.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenPayload {
    private String memberUUID;
    private String roles;
}
