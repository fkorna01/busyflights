package com.travix.medusa.busyflights.domain.controller;


import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.service.IBusyFlightServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/request")
public class BusyFlightsController {

    @Autowired
    public IBusyFlightServiceImpl busyFlightService;

    @RequestMapping(method = RequestMethod.GET, path = "busyFlights")
    public List<BusyFlightsResponse> getbusyFlights(@RequestBody BusyFlightsRequest request) {
        List<BusyFlightsResponse> response = new ArrayList<>();

        response = busyFlightService.searchFlights(request);

        return response;
    }


}
