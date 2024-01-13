package com.example.appkit.backoff;

import java.time.Duration;

public class Backoff {
    public static Duration exponentialBackoff(int currentCount, int waitBase) {
        var waitSecond = waitBase * Math.pow(2.0, currentCount);

        return Duration.ofSeconds((long) waitSecond);
    }
}
