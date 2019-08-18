package com.travix.medusa.busyflights.domain.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.request.IAirlineRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;


/**
 * @author Fotios Kornarakis
 */
@Service
public class BusyFlightServiceImpl implements IBusyFlightServiceImpl {
    private List<IAirlineRequest> airlineRequestOptions;

    public BusyFlightServiceImpl(List<IAirlineRequest> airlineRequestOptions) {
        this.airlineRequestOptions = airlineRequestOptions;
    }


    @Override
    public Stream<BusyFlightsResponse> searchFlights(BusyFlightsRequest request) {

        return airlineRequestOptions
                .parallelStream()
                .flatMap(airlineRequestOptions -> airlineRequestOptions
                        .convertObject(airlineRequestOptions.sendRequest
                                (airlineRequestOptions.getUrl(airlineRequestOptions)), airlineRequestOptions.getProviderName(airlineRequestOptions)).stream());
    }

}
