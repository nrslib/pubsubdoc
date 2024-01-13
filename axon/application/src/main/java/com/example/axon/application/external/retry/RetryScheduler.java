package com.example.axon.application.external.retry;

import com.example.appkit.backoff.Backoff;
import org.axonframework.eventhandling.scheduling.EventScheduler;

import java.time.Duration;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class RetryScheduler {
    public static void exponentialBackoff(EventScheduler scheduler, int currentCount, int waitBase, long max, Supplier<Object> ifThen) {
        Duration backoff = Backoff.exponentialBackoff(currentCount, waitBase);

        if (backoff.getSeconds() < max) {
            scheduler.schedule(backoff, ifThen.get());
        }
    }

    public static void exponentialBackoff(EventScheduler scheduler, int currentCount, int waitBase, long max, Consumer<Duration> elseThen, Supplier<Object> ifThen) {
        Duration backoff = Backoff.exponentialBackoff(currentCount, waitBase);

        if (backoff.getSeconds() < max) {
            scheduler.schedule(backoff, ifThen.get());
        } else {
            elseThen.accept(backoff);
        }
    }
}
