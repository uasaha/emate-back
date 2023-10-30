package me.emate.mateback.statistic.repository.impl;

import me.emate.mateback.statistic.entity.QStatistic;
import me.emate.mateback.statistic.entity.Statistic;
import me.emate.mateback.statistic.repository.StatisticRepositoryCustom;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import static com.querydsl.core.group.GroupBy.sum;

/**
 * Statistic repository의 구현체입니다.
 */
public class StatisticRepositoryImpl extends QuerydslRepositorySupport implements StatisticRepositoryCustom {
    public StatisticRepositoryImpl() {super(Statistic.class);}

    QStatistic statistic = QStatistic.statistic;

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getTotalVisitor() {
        return from(statistic)
                .select(statistic.num.sum())
                .fetchOne();
    }
}
