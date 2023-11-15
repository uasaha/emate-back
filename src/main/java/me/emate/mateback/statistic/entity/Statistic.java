package me.emate.mateback.statistic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Statistic entity class입니다.
 *
 * @author 여운석
 */
@Table(name = "statistic")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Statistic {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "statistic_no")
  private Integer statisticNo;

  @Column(name = "statistic_name")
  private String statisticName;

  @Column(name = "statistic_num")
  private Integer num;

  @Column(name = "base_time")
  private LocalDate baseDate;
}
