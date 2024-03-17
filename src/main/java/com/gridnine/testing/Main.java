package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flightsList = FlightBuilder.createFlights();

        FlightFilter arrivalBeforeDepartureSegmentFilter = new ArrivalBeforeDepartureSegmentFilter();
        FlightFilter departureBeforeCurrTimeFilter = new DepartureBeforeCurrTimeFilter();
        FlightFilter twoHoursGroundTimeExcessFilter = new TwoHoursGroundTimeExcessFilter();

        System.out.println("List with no flights departing in the past");
        System.out.println(arrivalBeforeDepartureSegmentFilter.filter(flightsList));

        System.out.println("List with no flights that depart before they arrive");
        System.out.println(departureBeforeCurrTimeFilter.filter(flightsList));

        System.out.println("List with no flights with more than two hours ground time");
        System.out.println(twoHoursGroundTimeExcessFilter.filter(flightsList));

    }
}
