package com.travix.medusa.busyflights.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.controller.BusyFlightsController;
import com.travix.medusa.busyflights.domain.service.IBusyFlightServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Fotios Kornarakis
 */


@RunWith(SpringRunner.class)
@WebMvcTest(value = BusyFlightsController.class)
public class BusyFligthtsControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private IBusyFlightServiceImpl busyFlightService;
    private List<BusyFlightsResponse> busyFlightsResponse = new ArrayList<>();


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


        Mockito.when(busyFlightService.searchFlights(Mockito.any(BusyFlightsRequest.class))).thenReturn((results));

        mvc.perform(get("http://localhost:8080/request/busyFlights")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new BusyFlightsRequest())))
                .andExpect(status().isOk())
                .andDo(resHandler -> {

                    String resultInJson = resHandler.getResponse().getContentAsString();

                    BusyFlightsResponse[] result = mapper.readValue(resultInJson, BusyFlightsResponse[].class);

                    assertEquals(results.size(), result.length);
                });
    }
}


