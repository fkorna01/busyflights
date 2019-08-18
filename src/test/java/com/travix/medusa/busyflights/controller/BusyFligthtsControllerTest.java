package com.travix.medusa.busyflights.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.controller.BusyFlightsController;
import com.travix.medusa.busyflights.domain.service.IBusyFlightServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

/**
 * @author Fotios Kornarakis
 */


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BusyFlightsController.class)
public class BusyFligthtsControllerTest {

    @Autowired
    private RestTemplate restTemplate;
    @MockBean
    private IBusyFlightServiceImpl busyFlightService;
    private List<BusyFlightsResponse> busyFlightsResponse = new ArrayList<>();

    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void getResultsInOrderTest() throws Exception {
        final List<BusyFlightsResponse> results = new ArrayList<>();

//        {
//            "airline": "EZY",
//                "supplier": "CRAZY",
//                "fare": 13.12,
//                "departureAirportCode": "HER",
//                "destinationAirportCode": "AMS",
//                "departureDate": "2019-12-10",
//                "arrivalDate": "2019-12-10"
//        },
//        {
//            "airline": "AEG",
//                "supplier": "CRAZY",
//                "fare": 15.23,
//                "departureAirportCode": "HER",
//                "destinationAirportCode": "AMS",
//                "departureDate": "2019-12-10",
//                "arrivalDate": "2019-12-10"
//        }

        results.add(new BusyFlightsResponse("EZY", "CRAZY", new BigDecimal("13.12"), "HER", "AMS", "2019-12-10", "2019-12-10"));
        results.add(new BusyFlightsResponse("EZY", "CRAZY", new BigDecimal("15.23"), "HER", "AMS", "2019-12-10", "2019-12-10"));

        final BusyFlightsRequest busyFlightsRequest = new BusyFlightsRequest("HER", "AMS", "2019-12-10", "2019-12-10", 2);

        mockServer.expect(ExpectedCount.once(), requestTo(new URI("http://localhost:8080/request/busyFlights")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .body(mapper.writeValueAsString(busyFlightsRequest)));

        mockServer.verify();

        Assert.assertEquals(busyFlightsRequest, busyFlightsRequest);

    }
}


