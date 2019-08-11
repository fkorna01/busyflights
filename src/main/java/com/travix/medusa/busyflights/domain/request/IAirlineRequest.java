package com.travix.medusa.busyflights.domain.request;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;

import java.util.List;

/**
 * @author Fotios Kornarakis
 */

public interface IAirlineRequest {

    StringBuffer sendRequest(String urls);

    List<BusyFlightsResponse> parseDetails(StringBuffer response, String site);

}

