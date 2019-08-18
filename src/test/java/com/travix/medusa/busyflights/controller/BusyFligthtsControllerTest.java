package com.travix.medusa.busyflights.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.controller.BusyFlightsController;
import com.travix.medusa.busyflights.domain.service.IBusyFlightServiceImpl;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertTrue;


/**
 * @author Fotios Kornarakis
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BusyFligthtsControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders requestHeaders = new HttpHeaders();

    @Test
    public void testCreateStudent() throws Exception {
        final List<BusyFlightsResponse> results = new ArrayList<>();


        BusyFlightsRequest busyFlightsRequest = new BusyFlightsRequest();
        busyFlightsRequest.setNumberOfPassengers(2);
        busyFlightsRequest.setReturnDate("2019-12-10");
        busyFlightsRequest.setDepartureDate("2019-12-10");
        busyFlightsRequest.setDestination("AMS");
        busyFlightsRequest.setOrigin("HER");

        HttpEntity<String> entity = new HttpEntity<>("{\n" +
                "  \"origin\": \"HER\",\n" +
                "  \"destination\": \"AMS\" ,\n" +
                "  \"departureDate\": \"2019-12-10\",\n" +
                "  \"returnDate\" : \"2019-12-10\",\n" +
                "  \"numberOfPassengers\": 2\n" +
                "}");

        ResponseEntity<BusyFlightsResponse> response = restTemplate.exchange(createURLWithPort(
                "/request/busyFlights"), HttpMethod.GET, entity, BusyFlightsResponse.class);

    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}


