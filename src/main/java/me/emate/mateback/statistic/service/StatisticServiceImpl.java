package me.emate.mateback.statistic.service;

import lombok.RequiredArgsConstructor;
import me.emate.mateback.statistic.entity.Statistic;
import me.emate.mateback.statistic.repository.StatisticRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Statistic service 구현체 입니다.
 *
 * @author 여운석
 */
@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {
    private final StatisticRepository statisticRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getTotalVisitor() {
        return statisticRepository.getTotalVisitor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTodayToTotal(int to) {
        LocalDate today = LocalDate.now();
        Statistic statistic = new Statistic(null, today.format(DateTimeFormatter.ofPattern("yyyyMMdd")), to, today);
        statisticRepository.save(statistic);
    }
}
