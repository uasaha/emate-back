package me.emate.mateback.contents.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Create contents request dto 입니다.
 *
 * @author 여운석
 */
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
