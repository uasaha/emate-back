package me.emate.mateback.statistic.dummy;

import me.emate.mateback.statistic.entity.Statistic;

import java.time.LocalDate;

public class StatisticDummy {
    public static Statistic dummy() {
        return new Statistic(1,
                "dummy",
                10,
                LocalDate.of(2023, 10, 30));
    }
}
