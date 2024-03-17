package com.gridnine.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TwoHoursGroundTimeExcessFilterTest {
    List<Flight> flightsToBeFiltered;
    FlightFilter flightFilter;
    List<Flight> filteredList;
    List<Flight> expectedResultList;

    @BeforeEach
    void init() {
        flightsToBeFiltered = new ArrayList<>();
        flightFilter = new TwoHoursGroundTimeExcessFilter();
        expectedResultList = new ArrayList<>();
        filteredList = new ArrayList<>();
    }

    @Test
    void filterListWithAllValidFlightsExpectEqual() {
        expectedResultList.add(createFlight(LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(2),
                LocalDateTime.now().plusDays(2).plusHours(3), LocalDateTime.now().plusDays(2).plusHours(5)));
        expectedResultList.add(createFlight(LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(2),
                LocalDateTime.now().plusDays(2).plusHours(3), LocalDateTime.now().plusDays(2).plusHours(5),
                LocalDateTime.now().plusDays(2).plusHours(6), LocalDateTime.now().plusDays(2).plusHours(10)));

        flightsToBeFiltered.add(createFlight(LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(2),
                LocalDateTime.now().plusDays(2).plusHours(3), LocalDateTime.now().plusDays(2).plusHours(5)));
        flightsToBeFiltered.add(createFlight(LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(2),
                LocalDateTime.now().plusDays(2).plusHours(3), LocalDateTime.now().plusDays(2).plusHours(5),
                LocalDateTime.now().plusDays(2).plusHours(6), LocalDateTime.now().plusDays(2).plusHours(10)));

        filteredList = flightFilter.filter(flightsToBeFiltered);

        Assertions.assertEquals(expectedResultList.toString(), filteredList.toString());
    }

    @Test
    void filterListWithAllInvalidFlightsExpectEmptyList() {
        expectedResultList.add(createFlight(LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(2),
                LocalDateTime.now().plusDays(2).plusHours(6), LocalDateTime.now().plusDays(2).plusHours(10)));
        expectedResultList.add(createFlight(LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(2),
                LocalDateTime.now().plusDays(2).plusHours(7), LocalDateTime.now().plusDays(2).plusHours(10),
                LocalDateTime.now().plusDays(2).plusHours(12), LocalDateTime.now().plusDays(2).plusHours(15)));

        filteredList = flightFilter.filter(flightsToBeFiltered);

        Assertions.assertTrue(filteredList.isEmpty());
    }

    @Test
    void filterListWithInvalidAndValidFlightsExpectEqual() {
        expectedResultList.add(createFlight(LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(2),
                LocalDateTime.now().plusDays(2).plusHours(3), LocalDateTime.now().plusDays(2).plusHours(5)));
        flightsToBeFiltered.add(createFlight(LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(2),
                LocalDateTime.now().plusDays(2).plusHours(3), LocalDateTime.now().plusDays(2).plusHours(5)));
        flightsToBeFiltered.add(createFlight(LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(2),
                LocalDateTime.now().plusDays(2).plusHours(6), LocalDateTime.now().plusDays(2).plusHours(10)));

        filteredList = flightFilter.filter(flightsToBeFiltered);

        Assertions.assertEquals(expectedResultList.toString(), filteredList.toString());
    }

    private Flight createFlight(final LocalDateTime... dates) {
        if ((dates.length % 2) != 0) {
            throw new IllegalArgumentException(
                    "you must pass an even number of dates");
        }
        List<Segment> segments = new ArrayList<>(dates.length / 2);
        for (int i = 0; i < (dates.length - 1); i += 2) {
            segments.add(new Segment(dates[i], dates[i + 1]));
        }
        return new Flight(segments);
    }
}
