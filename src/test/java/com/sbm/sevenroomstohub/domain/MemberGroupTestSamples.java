package com.sbm.sevenroomstohub.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class MemberGroupTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static MemberGroup getMemberGroupSample1() {
        return new MemberGroup().id(1L);
    }

    public static MemberGroup getMemberGroupSample2() {
        return new MemberGroup().id(2L);
    }

    public static MemberGroup getMemberGroupRandomSampleGenerator() {
        return new MemberGroup().id(longCount.incrementAndGet());
    }
}
