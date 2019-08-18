package com.travix.medusa.busyflights.domain.request;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


/**
 * @author Fotios Kornarakis
 */

@Service
public class CrazyFlightRequestCall implements IAirlineRequest {
    private static final String URL_PROVIDER = "http://localhost:8080/crazyFlights/crazyFligthDataFetch";
    private static final String PROVIDER = "CrazyFlight";
    private static final Logger log = Logger.getLogger(CrazyFlightRequestCall.class.getName());

    @Override
    public String getUrl(IAirlineRequest providerName) {
        return URL_PROVIDER;
    }

    @Override
    public String getProviderName(IAirlineRequest providerName) {
        return PROVIDER;
    }


    @Override
    public Object sendRequest(String url) {
        RestTemplate restTemplate = new RestTemplate();
        Object mapper = restTemplate.getForObject(url, String.class);
        return mapper;
    }


    @Override
    public List<BusyFlightsResponse> convertObject(Object response, String site) {

        List<BusyFlightsResponse> busyFlightsResponses = new ArrayList<>();
        try {
            List<CrazyAirResponse> crazyAirResponses = new ObjectMapper().readValue(response.toString(), new TypeReference<List<CrazyAirResponse>>() {
            });
            log.info("Converting Object to CrazyAirResponse");
            for (CrazyAirResponse obj : crazyAirResponses) {
                BusyFlightsResponse busyFlightsResponse = new BusyFlightsResponse();
                busyFlightsResponse.setDestinationAirportCode(obj.getDestinationAirportCode());
                busyFlightsResponse.setDepartureAirportCode(obj.getDepartureAirportCode());
                busyFlightsResponse.setSupplier(site);
                busyFlightsResponse.setFare(obj.getPrice());
                busyFlightsResponse.setArrivalDate(obj.getArrivalDate());
                busyFlightsResponse.setAirline(obj.getAirline());
                busyFlightsResponse.setDepartureDate(obj.getDepartureDate());
                busyFlightsResponses.add(busyFlightsResponse);
            }

            return busyFlightsResponses;
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
        return busyFlightsResponses;

    }

    @Override
    public String toString() {
        return PROVIDER;
    }
}
