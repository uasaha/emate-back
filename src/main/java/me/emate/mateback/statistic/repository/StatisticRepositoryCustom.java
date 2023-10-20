package me.emate.mateback.statistic.repository;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface StatisticRepositoryCustom {
    Integer getTotalVisitor();
}
