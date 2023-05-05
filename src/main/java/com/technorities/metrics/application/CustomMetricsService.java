package com.technorities.metrics.application;

import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Denis Yakovenko
 */
@Component
@Slf4j
public class CustomMetricsService {
    private final AtomicInteger g1t1;
    private final AtomicInteger g1t2;
    private final AtomicInteger g1t3;
    private final AtomicInteger g2t1;
    private final AtomicInteger g2t2;
    private final AtomicInteger g2t3;

    private Map<String, AtomicInteger> counters = new HashMap<>();
    @Autowired
    CompositeMeterRegistry meterRegistry;

    public CustomMetricsService(CompositeMeterRegistry meterRegistry) {
        g1t1 = meterRegistry.gauge("space.group1.t1", new AtomicInteger(0));
        g1t2 = meterRegistry.gauge("space.group1.t2", new AtomicInteger(0));
        g1t3 = meterRegistry.gauge("space.group1.t3", new AtomicInteger(0));
        g2t1 = meterRegistry.gauge("space.group2.t1", new AtomicInteger(0));
        g2t2 = meterRegistry.gauge("space.group2.t2", new AtomicInteger(0));
        g2t3 = meterRegistry.gauge("space.group2.t3", new AtomicInteger(0));
        counters.put("space.group1.t1", g1t1);
        counters.put("space.group1.t2", g1t2);
        counters.put("space.group1.t3", g1t3);
        counters.put("space.group2.t1", g2t1);
        counters.put("space.group2.t2", g2t2);
        counters.put("space.group2.t3", g2t3);
    }

    public void setUpMetric(String name, int value) {
        log.info("new value. {} = {}", name, value);
        counters.get(name).set(value);
    }
}
