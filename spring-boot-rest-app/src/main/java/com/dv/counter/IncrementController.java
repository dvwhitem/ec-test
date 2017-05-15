package com.dv.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.buffer.BufferMetricReader;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.LongAdder;

/**
 * Created by vitaliy on 5/10/17.
 */
@CrossOrigin
@RestController
public class IncrementController {

    private CounterService counterService;

    private BufferMetricReader metrics;

    @Autowired
    public IncrementController(CounterService counterService, BufferMetricReader metrics) {
        this.counterService = counterService;
        this.metrics = metrics;
    }

    @GetMapping("/counter")
    public int couner() {
        counterService.increment("counter");
        return metrics.findOne("counter").getValue().intValue();
    }

}
