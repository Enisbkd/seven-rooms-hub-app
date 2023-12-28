package com.sbm.sevenroomstohub.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TableNumberTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static TableNumber getTableNumberSample1() {
        return new TableNumber().id(1L).tableNum(1);
    }

    public static TableNumber getTableNumberSample2() {
        return new TableNumber().id(2L).tableNum(2);
    }

    public static TableNumber getTableNumberRandomSampleGenerator() {
        return new TableNumber().id(longCount.incrementAndGet()).tableNum(intCount.incrementAndGet());
    }
}
