package me.emate.mateback.statistic.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.mateback.statistic.service.StatisticService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Statistic controller(일일방문자 통계)입니다.
 *
 * @author 여운석
 */
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/visitor")
public class StatisticController {
    private final StatisticService statisticService;

    /**
     * Gets total visitor.
     *
     * @return the total visitor
     */
    @GetMapping("/total")
    public ResponseEntity<Integer> getTotalVisitor() {
        return ResponseEntity.ok().body(statisticService.getTotalVisitor());
    }

    /**
     * Sets today to total.
     *
     * @param today the today
     * @return the today to total
     */
    @PostMapping("/{today}")
    public ResponseEntity<Void> setTodayToTotal(@PathVariable String today) {
        int to = Integer.parseInt(today);
        statisticService.setTodayToTotal(to);

        return ResponseEntity.ok().build();
    }
}
