package com.travix.medusa.busyflights.domain.thirdpartyresource;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/crazyFlights")
public class CrazyFlightsResource {
//third party api it can return data from crazyFlight

    @RequestMapping(method = RequestMethod.GET, path = "crazyFligthDataFetch")
    public List<CrazyAirResponse> getCrazyAir(CrazyAirRequest request) {
        List<CrazyAirResponse> flights = new LinkedList<>();
        flights.add(new CrazyAirResponse("AEG", new BigDecimal("15.23"), "AA", "HER", "AMS", "2019-12-10", "2019-12-10"));
        flights.add(new CrazyAirResponse("BA", new BigDecimal("33.1231"), "BB", "ATH", "LRN", "2019-12-10", "2019-12-10"));
        flights.add(new CrazyAirResponse("AA", new BigDecimal("12.54"), "CC", "LHT", "BCN", "2020-10-10", "2020-10-10"));
        flights.add(new CrazyAirResponse("EZY", new BigDecimal("55.12"), "AA", "ADL", "IND", "2021-10-10", "2021-10-10"));
        flights.add(new CrazyAirResponse("EZY", new BigDecimal("13.12"), "BB", "HER", "AMS", "2019-12-10", "2019-12-10"));

        return flights;
    }
}
