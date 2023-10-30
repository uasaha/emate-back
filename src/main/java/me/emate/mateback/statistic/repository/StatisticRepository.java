package me.emate.mateback.statistic.repository;

import me.emate.mateback.statistic.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA사용을 위한 Statistic repository 입니다.
 */
public interface StatisticRepository extends JpaRepository<Statistic, String>, StatisticRepositoryCustom {
}
