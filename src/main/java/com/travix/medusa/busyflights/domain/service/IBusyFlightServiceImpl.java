package com.travix.medusa.busyflights.domain.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;

import java.util.stream.Stream;


/**
 * @author Fotios Kornarakis
 */
public interface IBusyFlightServiceImpl {

    /**
     * sends a parallel request into the providers and collects them into a Stream.
     * @param request
     * @return response
     */
    Stream<BusyFlightsResponse> searchFlights(BusyFlightsRequest request);
}
