package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.request.AirlineRequest;
import com.travix.medusa.busyflights.domain.request.IAirlineRequest;
import com.travix.medusa.busyflights.domain.service.BusyFlightServiceImpl;
import com.travix.medusa.busyflights.domain.service.IBusyFlightServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.travix.medusa.busyflights.domain.utils.FlightSearchConstants.FLIGHTURLS;
import static org.junit.Assert.assertEquals;

/**
 * @author  Fotios Kornarakis
 */


public class BusyFligthtsServiceImplTest {



    @Test
    public void searchFlightsTestSearchTestTheOrder() {
        IAirlineRequest apiService = new AirlineRequest();

        BusyFlightsRequest busyFlightsRequest = new BusyFlightsRequest( "HER", "AMS" , "2019-12-10", "2019-12-10", 2);
        List<BusyFlightsResponse> busyFlightsResponses = new ArrayList<>();
        for(int i = 0 ; i < FLIGHTURLS.length; i++) {
            busyFlightsResponses.addAll(apiService.parseDetails(apiService.sendRequest(FLIGHTURLS[i][0]), FLIGHTURLS[i][1]));

        }

        assertEquals(11, busyFlightsResponses.size());
    }
}
