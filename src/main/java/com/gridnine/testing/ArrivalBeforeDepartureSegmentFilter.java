package com.gridnine.testing;

import java.util.List;
import java.util.stream.Collectors;

public class ArrivalBeforeDepartureSegmentFilter implements FlightFilter {
    @Override
    public List<Flight> filter(List<Flight> flightsList) {
        List<Flight> filteredList = flightsList.stream()
                .filter(flight -> flight.getSegments().stream().anyMatch(segment -> segment.getDepartureDate().isBefore(segment.getArrivalDate())))
                .collect(Collectors.toList());
        return filteredList;
    }
}
