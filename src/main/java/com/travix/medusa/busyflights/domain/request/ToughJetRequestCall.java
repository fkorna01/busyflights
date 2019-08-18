package com.travix.medusa.busyflights.domain.request;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
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
public class ToughJetRequestCall implements IAirlineRequest {
    private static final String URL_PROVIDER = "http://localhost:8080/toughJet/toughJetDataFetch";
    private static final String PROVIDER = "ToughJet";
    private static final Logger log = Logger.getLogger(ToughJetRequestCall.class.getName());


    @Override
    public String getUrl(IAirlineRequest providerName) {
        return URL_PROVIDER;
    }

    @Override
    public String getProviderName(IAirlineRequest providerName) {
        return PROVIDER;
    }

    /**
     * sends a get request into a "third party provider" and adds it into a string buffer
     *
     * @param url
     * @return Object
     */
    @Override
    public Object sendRequest(String url) {
        RestTemplate restTemplate = new RestTemplate();
        Object mapper = restTemplate.getForObject(url, String.class);
        return mapper;
    }


    @Override
    public List<BusyFlightsResponse> convertObject(Object response, String site) {

        List<BusyFlightsResponse> busyFlightsResponses = new ArrayList<>();
        log.info("Converting Object to ToughResponse");
        try {
            List<ToughJetResponse> responses = new ObjectMapper().readValue(response.toString(), new TypeReference<List<ToughJetResponse>>() {
            });
            for (ToughJetResponse obj : responses) {
                BusyFlightsResponse busyFlightsResponse = new BusyFlightsResponse();
                busyFlightsResponse.setAirline(obj.getCarrier());
                busyFlightsResponse.setArrivalDate(obj.getInboundDateTime());
                busyFlightsResponse.setFare(obj.getBasePrice());
                busyFlightsResponse.setDepartureDate(obj.getOutboundDateTime());
                busyFlightsResponse.setSupplier(site);
                busyFlightsResponse.setDepartureAirportCode(obj.getDepartureAirportName());
                busyFlightsResponse.setDestinationAirportCode(obj.getArrivalAirportName());
                busyFlightsResponses.add(busyFlightsResponse);
            }
            return busyFlightsResponses;
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
        return null;

    }

    @Override
    public String toString() {
        return "ToughJet";
    }
}
