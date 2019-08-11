package com.travix.medusa.busyflights.domain.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.request.IAirlineRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.travix.medusa.busyflights.domain.utils.FlightSearchConstants.*;

/**
 * @author  Fotios Kornarakis
 */
@Service
public class BusyFlightServiceImpl implements IBusyFlightServiceImpl {


    @Autowired
    IAirlineRequest apiService;

    /**
     * contains a list of flights ordered by fare
     * @param request
     * @return response
     */
    @Override
    public List<BusyFlightsResponse> searchFlights(BusyFlightsRequest request) {
        List<BusyFlightsResponse> busyFlightsResponses = new ArrayList<>();

        for(int i = 0 ; i < FLIGHTURLS.length; i++) {
            busyFlightsResponses.addAll(apiService.parseDetails(apiService.sendRequest(FLIGHTURLS[i][0]), FLIGHTURLS[i][1]));

        }

        return busyFlightsResponses.stream().filter(p -> p.getDepartureAirportCode().equals(request.getOrigin()) && p.getArrivalDate().
                equals(request.getReturnDate()) && p.getDestinationAirportCode().equals(request.getDestination()) && p.getDepartureDate().equals(request.getDepartureDate()))
                .sorted(Comparator.comparing(BusyFlightsResponse::getFare)).collect(Collectors.toList());
    }


}
