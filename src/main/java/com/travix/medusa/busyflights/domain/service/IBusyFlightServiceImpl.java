package com.travix.medusa.busyflights.domain.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;

import java.util.List;


/**
 * @author  Fotios Kornarakis
 */
public interface IBusyFlightServiceImpl {

    List<BusyFlightsResponse> searchFlights(BusyFlightsRequest request);
}
