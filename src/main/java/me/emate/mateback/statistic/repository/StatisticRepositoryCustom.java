package me.emate.mateback.statistic.repository;

import org.springframework.data.repository.NoRepositoryBean;

/**
 * Quesydsl 사용을 위한 Statistic repository 입니다.
 */
@NoRepositoryBean
public interface StatisticRepositoryCustom {

  /**
   * Gets total visitor.
   *
   * @return the total visitor
   */
  Integer getTotalVisitor();
}
