package com.sbm.sevenroomstohub.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TableNumTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static TableNum getTableNumSample1() {
        return new TableNum().id(1L).tableNumber(1);
    }

    public static TableNum getTableNumSample2() {
        return new TableNum().id(2L).tableNumber(2);
    }

    public static TableNum getTableNumRandomSampleGenerator() {
        return new TableNum().id(longCount.incrementAndGet()).tableNumber(intCount.incrementAndGet());
    }
}
