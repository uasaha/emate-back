package me.emate.mateback.statistic.repository;

import me.emate.mateback.statistic.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticRepository extends JpaRepository<Statistic, String>, StatisticRepositoryCustom {
}
