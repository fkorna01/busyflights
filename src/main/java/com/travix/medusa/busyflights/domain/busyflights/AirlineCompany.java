package com.travix.medusa.busyflights.domain.busyflights;

public class AirlineCompany {

    private String url;
    private String airlineName;

    public AirlineCompany(String url, String airlineName) {
        this.url = url;
        this.airlineName = airlineName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }
}
