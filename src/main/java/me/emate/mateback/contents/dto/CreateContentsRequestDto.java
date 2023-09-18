package me.emate.mateback.contents.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateContentsRequestDto {
    private Integer categoryNo;
    private Integer tagNo;
    private String subject;
    private String detail;
}
