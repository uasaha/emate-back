package me.emate.mateback.statistic.service;

import lombok.RequiredArgsConstructor;
import me.emate.mateback.statistic.entity.Statistic;
import me.emate.mateback.statistic.repository.StatisticRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {
    private final StatisticRepository statisticRepository;

    @Override
    public Integer getTotalVisitor() {
        return statisticRepository.getTotalVisitor();
    }

    @Override
    public void setTodayToTotal(int to) {
        LocalDate today = LocalDate.now();
        Statistic statistic = new Statistic(null, today.format(DateTimeFormatter.ofPattern("YYYYMMdd")), to, today);
        statisticRepository.save(statistic);
    }
}
