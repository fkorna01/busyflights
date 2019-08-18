package com.travix.medusa.busyflights.domain.controller;


import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.service.IBusyFlightServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/request")
public class BusyFlightsController {
    @Autowired
    public IBusyFlightServiceImpl busyFlightService;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping(method = RequestMethod.GET, path = "busyFlights")
    public List<BusyFlightsResponse> getbusyFlights(@RequestBody BusyFlightsRequest request) {

        // controller filters and returns from a stream converts to List
        return busyFlightService.searchFlights(request).filter(p -> p.getDepartureAirportCode().equals(request.getOrigin()) && p.getArrivalDate().
                equals(request.getReturnDate()) && p.getDestinationAirportCode().equals(request.getDestination()) && p.getDepartureDate().equals(request.getDepartureDate()))
                .sorted(Comparator.comparing(BusyFlightsResponse::getFare)).collect(Collectors.toList());

    }


}
