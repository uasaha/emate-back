package me.emate.mateback.statistic.service;

/**
 * Statistic service입니다.
 *
 * @author 여운석
 */
public interface StatisticService {

  /**
   * Gets total visitor.
   *
   * @return the total visitor
   */
  Integer getTotalVisitor();

  /**
   * Sets today to total.
   *
   * @param to the to
   */
  void setTodayToTotal(int to);
}
