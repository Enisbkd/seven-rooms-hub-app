package com.sbm.sevenroomstohub.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ClientVenueStatsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static ClientVenueStats getClientVenueStatsSample1() {
        return new ClientVenueStats()
            .id(1L)
            .venueId("venueId1")
            .avgRating(1)
            .bookedByNames("bookedByNames1")
            .lastVisitDate("lastVisitDate1")
            .numRatings(1)
            .totalCancellations(1)
            .totalCovers(1)
            .totalNoShows(1)
            .totalVisit(1)
            .venueMarketingOptints("venueMarketingOptints1")
            .techLineage("techLineage1")
            .techMapping("techMapping1")
            .techComment("techComment1");
    }

    public static ClientVenueStats getClientVenueStatsSample2() {
        return new ClientVenueStats()
            .id(2L)
            .venueId("venueId2")
            .avgRating(2)
            .bookedByNames("bookedByNames2")
            .lastVisitDate("lastVisitDate2")
            .numRatings(2)
            .totalCancellations(2)
            .totalCovers(2)
            .totalNoShows(2)
            .totalVisit(2)
            .venueMarketingOptints("venueMarketingOptints2")
            .techLineage("techLineage2")
            .techMapping("techMapping2")
            .techComment("techComment2");
    }

    public static ClientVenueStats getClientVenueStatsRandomSampleGenerator() {
        return new ClientVenueStats()
            .id(longCount.incrementAndGet())
            .venueId(UUID.randomUUID().toString())
            .avgRating(intCount.incrementAndGet())
            .bookedByNames(UUID.randomUUID().toString())
            .lastVisitDate(UUID.randomUUID().toString())
            .numRatings(intCount.incrementAndGet())
            .totalCancellations(intCount.incrementAndGet())
            .totalCovers(intCount.incrementAndGet())
            .totalNoShows(intCount.incrementAndGet())
            .totalVisit(intCount.incrementAndGet())
            .venueMarketingOptints(UUID.randomUUID().toString())
            .techLineage(UUID.randomUUID().toString())
            .techMapping(UUID.randomUUID().toString())
            .techComment(UUID.randomUUID().toString());
    }
}
