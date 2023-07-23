package me.emate.mateback.statistic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "statistic")
@Entity
@Getter
@NoArgsConstructor
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statistic_name")
    private String statisticName;

    @Column(name = "statistic_num")
    private Integer num;
}
