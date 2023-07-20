package me.emate.mateback.statistic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "statistic")
@Entity
@Getter
@NoArgsConstructor
public class Statistic {
    @Id
    @Column(name = "statistic_name")
    private String statisticName;

    @Column(name = "statistic_num")
    private Integer num;
}
