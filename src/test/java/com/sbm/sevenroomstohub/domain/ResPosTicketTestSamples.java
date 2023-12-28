package com.sbm.sevenroomstohub.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ResPosTicketTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static ResPosTicket getResPosTicketSample1() {
        return new ResPosTicket()
            .id(1L)
            .status("status1")
            .code(1)
            .tableNo("tableNo1")
            .businessId(1)
            .localPosticketId("localPosticketId1")
            .employeeName("employeeName1")
            .startTime("startTime1")
            .endtime("endtime1")
            .techLineage("techLineage1")
            .techMapping("techMapping1")
            .techComment("techComment1");
    }

    public static ResPosTicket getResPosTicketSample2() {
        return new ResPosTicket()
            .id(2L)
            .status("status2")
            .code(2)
            .tableNo("tableNo2")
            .businessId(2)
            .localPosticketId("localPosticketId2")
            .employeeName("employeeName2")
            .startTime("startTime2")
            .endtime("endtime2")
            .techLineage("techLineage2")
            .techMapping("techMapping2")
            .techComment("techComment2");
    }

    public static ResPosTicket getResPosTicketRandomSampleGenerator() {
        return new ResPosTicket()
            .id(longCount.incrementAndGet())
            .status(UUID.randomUUID().toString())
            .code(intCount.incrementAndGet())
            .tableNo(UUID.randomUUID().toString())
            .businessId(intCount.incrementAndGet())
            .localPosticketId(UUID.randomUUID().toString())
            .employeeName(UUID.randomUUID().toString())
            .startTime(UUID.randomUUID().toString())
            .endtime(UUID.randomUUID().toString())
            .techLineage(UUID.randomUUID().toString())
            .techMapping(UUID.randomUUID().toString())
            .techComment(UUID.randomUUID().toString());
    }
}
