package com.travix.medusa.busyflights.domain.request;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;

import java.util.List;

/**
 * @author Fotios Kornarakis
 */

public interface IAirlineRequest {

    /**
     * sends a get request into a "third party provider" and adds it into an Object
     *
     * @param url
     * @return Object
     */

    Object sendRequest(String url);

    /**
     * returns url of the Supplier
     *
     * @param providerName
     * @return Object
     */

    String getUrl(IAirlineRequest providerName);

    /**
     * returns name of the supplier
     *
     * @param providerName
     * @return String
     */
    String getProviderName(IAirlineRequest providerName);

    /**
     * takes an Object and the site of the response and retunrs a List with the responses
     * <p>
     * As providers might return objects in different format it will be necessary to add a case for every provider.
     *
     * @param response, site
     * @return List of BusyFligthtsResponse
     */

    List<BusyFlightsResponse> convertObject(Object response, String site);

}

