package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class DepartureBeforeCurrTimeFilter implements FlightFilter {
    @Override
    public List<Flight> filter(List<Flight> flightsList) {
        List<Flight> filteredList = flightsList.stream()
                .filter(flight -> flight.getSegments().stream().anyMatch(segment -> segment.getDepartureDate().isAfter(LocalDateTime.now())))
                .collect(Collectors.toList());
        return filteredList;
    }
}
