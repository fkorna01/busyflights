package com.travix.medusa.busyflights.domain.toughjet;

import java.math.BigDecimal;

/**
 * @author Fotios Kornarakis
 */
public class ToughJetResponse {
    //I have changed basePrice to Big Decimal as can be easily allocated or use it without having fraction issues
    private String carrier;
    private BigDecimal basePrice;
    private BigDecimal tax;
    private BigDecimal discount;
    private String departureAirportName;
    private String arrivalAirportName;
    private String outboundDateTime;
    private String inboundDateTime;

    public ToughJetResponse(String carrier, BigDecimal basePrice, BigDecimal tax, BigDecimal discount, String departureAirportName, String arrivalAirportName, String outboundDateTime, String inboundDateTime) {
        this.carrier = carrier;
        this.basePrice = basePrice;
        this.tax = tax;
        this.discount = discount;
        this.departureAirportName = departureAirportName;
        this.arrivalAirportName = arrivalAirportName;
        this.outboundDateTime = outboundDateTime;
        this.inboundDateTime = inboundDateTime;
    }

    public ToughJetResponse() {
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(final String carrier) {
        this.carrier = carrier;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(final BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(final BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(final BigDecimal discount) {
        this.discount = discount;
    }

    public String getDepartureAirportName() {
        return departureAirportName;
    }

    public void setDepartureAirportName(final String departureAirportName) {
        this.departureAirportName = departureAirportName;
    }

    public String getArrivalAirportName() {
        return arrivalAirportName;
    }

    public void setArrivalAirportName(final String arrivalAirportName) {
        this.arrivalAirportName = arrivalAirportName;
    }

    public String getOutboundDateTime() {
        return outboundDateTime;
    }

    public void setOutboundDateTime(final String outboundDateTime) {
        this.outboundDateTime = outboundDateTime;
    }

    public String getInboundDateTime() {
        return inboundDateTime;
    }

    public void setInboundDateTime(final String inboundDateTime) {
        this.inboundDateTime = inboundDateTime;
    }
}
