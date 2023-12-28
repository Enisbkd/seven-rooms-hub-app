package com.sbm.sevenroomstohub.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TableTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Table getTableSample1() {
        return new Table().id(1L).tableNumber(1);
    }

    public static Table getTableSample2() {
        return new Table().id(2L).tableNumber(2);
    }

    public static Table getTableRandomSampleGenerator() {
        return new Table().id(longCount.incrementAndGet()).tableNumber(intCount.incrementAndGet());
    }
}
