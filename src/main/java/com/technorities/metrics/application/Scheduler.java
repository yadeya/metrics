package com.technorities.metrics.application;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Denis Yakovenko
 */
@Component
@Slf4j
public class Scheduler {

    @Autowired
    CustomMetricsService customMetricsService;

    @Scheduled(fixedRate = 5000)
    public void getMeasuredValues() {
        log.info("getMeasuredValues");
        customMetricsService.setUpMetric("space.group1.t1", calc(50, 2, 0));
        customMetricsService.setUpMetric("space.group1.t2", calc(50, 5, 5));
        customMetricsService.setUpMetric("space.group1.t3", calc(55, 8, 30));
        customMetricsService.setUpMetric("space.group2.t1", calc(-130, 5, 0));
        customMetricsService.setUpMetric("space.group2.t2", calc(-130, 3, 5));
        customMetricsService.setUpMetric("space.group2.t3", calc(-130, 25, 30));

    }

    private int calc(int base, int amp, int shift) {
        return (int) (base +  amp * Math.sin(shift + ZonedDateTime.now().toInstant().toEpochMilli()));
    }
}
