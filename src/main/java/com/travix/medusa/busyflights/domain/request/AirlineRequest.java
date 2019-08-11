package com.travix.medusa.busyflights.domain.request;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.travix.medusa.busyflights.domain.utils.FlightSearchConstants.CRAZY;
import static com.travix.medusa.busyflights.domain.utils.FlightSearchConstants.TOUGH;


/**
 * @author  Fotios Kornarakis
 */

@Service
public class AirlineRequest implements IAirlineRequest {

    private static final Logger log = Logger.getLogger( AirlineRequest.class.getName() );
    @Override
    public StringBuffer sendRequest(String url) {
        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.addRequestProperty("Content-Type", "application/" + "GET");
            int responseCode = connection.getResponseCode();
            log.warning("Response Code: " + responseCode);
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;

                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {

                    response.append(inputLine);
                }
                in.close();

                return response;
            }
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<BusyFlightsResponse> parseDetails(StringBuffer response, String site) {

        List<BusyFlightsResponse> busyFlightsResponses = new ArrayList<>();
        switch (site) {
            case CRAZY:
                try {
                    List<CrazyAirResponse> crazyAirResponses = new ObjectMapper().readValue(response.toString(), new TypeReference<List<CrazyAirResponse>>() {
                    });
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
            case TOUGH:
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
            default:
                throw new RuntimeException("The " + site + " is unknown!");
        }
    }
}
