package me.emate.mateback.contents.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateContentsRequestDto {
    private Integer categoryNo;
    private List<Integer> tagNo;
    private Boolean hidden;
    private String thumbnail;
    private String subject;
    private String detail;
}
