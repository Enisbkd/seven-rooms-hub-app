package com.sbm.sevenroomstohub.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ClientTagTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ClientTag getClientTagSample1() {
        return new ClientTag()
            .id(1L)
            .tag("tag1")
            .tagDisplay("tagDisplay1")
            .group("group1")
            .groupDisplay("groupDisplay1")
            .color("color1")
            .techLineage("techLineage1")
            .techMapping("techMapping1")
            .techComment("techComment1");
    }

    public static ClientTag getClientTagSample2() {
        return new ClientTag()
            .id(2L)
            .tag("tag2")
            .tagDisplay("tagDisplay2")
            .group("group2")
            .groupDisplay("groupDisplay2")
            .color("color2")
            .techLineage("techLineage2")
            .techMapping("techMapping2")
            .techComment("techComment2");
    }

    public static ClientTag getClientTagRandomSampleGenerator() {
        return new ClientTag()
            .id(longCount.incrementAndGet())
            .tag(UUID.randomUUID().toString())
            .tagDisplay(UUID.randomUUID().toString())
            .group(UUID.randomUUID().toString())
            .groupDisplay(UUID.randomUUID().toString())
            .color(UUID.randomUUID().toString())
            .techLineage(UUID.randomUUID().toString())
            .techMapping(UUID.randomUUID().toString())
            .techComment(UUID.randomUUID().toString());
    }
}
