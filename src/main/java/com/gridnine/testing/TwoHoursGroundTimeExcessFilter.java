package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TwoHoursGroundTimeExcessFilter implements FlightFilter {
    @Override
    public List<Flight> filter(List<Flight> flightsList) {
        List<Flight> filteredList = new ArrayList<>();
        for (Flight flight : flightsList) {
            List<Segment> segments = flight.getSegments();
            if (segments.size() == 1) {
                filteredList.add(flight);
            } else {
                Duration duration = Duration.ZERO;
                for (int i = 0; i < segments.size() - 1; i++) {
                    LocalDateTime firstSegmentArrival = segments.get(i).getArrivalDate();
                    LocalDateTime secondSegmentDeparture = segments.get(i + 1).getDepartureDate();
                    duration = duration.plus(Duration.between(firstSegmentArrival, secondSegmentDeparture).abs());
                }
                if (duration.toHours() <= 2) {
                    filteredList.add(flight);
                }
            }
        }
        return filteredList;
    }
}
