package me.emate.mateback.statistic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
