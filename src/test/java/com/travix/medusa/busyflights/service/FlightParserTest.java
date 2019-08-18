//package com.travix.medusa.busyflights.service;
//
//import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
//import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
//import com.travix.medusa.busyflights.domain.request.CrazyFlightRequestCall;
//import com.travix.medusa.busyflights.domain.request.IAirlineRequest;
//import org.junit.Test;
//
//import java.math.BigDecimal;
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static org.junit.Assert.assertEquals;
//
//
//public class FlightParserTest {
//
//
//    @Test
//    public void normalFeedInputCrazyTest() {
//        StringBuffer stringBuffer = new StringBuffer("[{\"airline\":\"AEG\",\"price\":15.23,\"cabinclass\":\"AA\",\"departureAirportCode\":\"HER\",\"destinationAirportCode\":\"AMS\",\"departureDate\":\"2019-12-10\",\"arrivalDate\":\"2019-12-10\"},{\"airline\":\"BA\",\"price\":33.1231,\"cabinclass\":\"BB\",\"departureAirportCode\":\"ATH\",\"destinationAirportCode\":\"LRN\",\"departureDate\":\"2019-12-10\",\"arrivalDate\":\"2019-12-10\"},{\"airline\":\"AA\",\"price\":12.54,\"cabinclass\":\"CC\",\"departureAirportCode\":\"LHT\",\"destinationAirportCode\":\"BCN\",\"departureDate\":\"2020-10-10\",\"arrivalDate\":\"2020-10-10\"},{\"airline\":\"EZY\",\"price\":55.12,\"cabinclass\":\"AA\",\"departureAirportCode\":\"ADL\",\"destinationAirportCode\":\"IND\",\"departureDate\":\"2021-10-10\",\"arrivalDate\":\"2021-10-10\"},{\"airline\":\"EZY\",\"price\":13.12,\"cabinclass\":\"BB\",\"departureAirportCode\":\"HER\",\"destinationAirportCode\":\"AMS\",\"departureDate\":\"2019-12-10\",\"arrivalDate\":\"2019-12-10\"}]");
//        IAirlineRequest airlineRequest = new CrazyFlightRequestCall();
//        List<BusyFlightsResponse> busyFlightsResponses = airlineRequest.parseDetails(stringBuffer, "CRAZY");
//        assertEquals(5, busyFlightsResponses.size());
//    }
//
//    @Test
//    public void normalFeedInputToughTest() {
//        StringBuffer stringBuffer = new StringBuffer("[{\"carrier\":\"AEG\",\"basePrice\":55.65,\"tax\":22.21,\"discount\":1.15,\"departureAirportName\":\"HER\",\"arrivalAirportName\":\"AMS\",\"outboundDateTime\":\"2018-10-10\",\"inboundDateTime\":\"2018-10-10\"},{\"carrier\":\"AEG\",\"basePrice\":222.23,\"tax\":13.12,\"discount\":2.15,\"departureAirportName\":\"ATH\",\"arrivalAirportName\":\"LRN\",\"outboundDateTime\":\"2018-10-10\",\"inboundDateTime\":\"2018-10-10\"},{\"carrier\":\"AEG\",\"basePrice\":35.34,\"tax\":30.2,\"discount\":3.15,\"departureAirportName\":\"BCR\",\"arrivalAirportName\":\"LHT\",\"outboundDateTime\":\"2018-10-10\",\"inboundDateTime\":\"2018-10-10\"},{\"carrier\":\"BB\",\"basePrice\":22.34,\"tax\":19.2,\"discount\":4.15,\"departureAirportName\":\"CC\",\"arrivalAirportName\":\"LHT\",\"outboundDateTime\":\"2018-10-10\",\"inboundDateTime\":\"2018-10-10\"},{\"carrier\":\"AA\",\"basePrice\":128.55,\"tax\":19.2,\"discount\":5.15,\"departureAirportName\":\"CC\",\"arrivalAirportName\":\"JRK\",\"outboundDateTime\":\"2018-10-10\",\"inboundDateTime\":\"2018-10-10\"},{\"carrier\":\"EZY\",\"basePrice\":201.22,\"tax\":30.89,\"discount\":6.15,\"departureAirportName\":\"LGW\",\"arrivalAirportName\":\"JRK\",\"outboundDateTime\":\"2018-10-18\",\"inboundDateTime\":\"2018-10-25\"}]\n");
//        IAirlineRequest airlineRequest = new AirlineRequest();
//        List<BusyFlightsResponse> busyFlightsResponses = airlineRequest.parseDetails(stringBuffer, "TOUGH");
//        assertEquals(6, busyFlightsResponses.size());
//    }
//
//    @Test(expected = RuntimeException.class)
//    public void siteNotExistTest() {
//        StringBuffer stringBuffer = new StringBuffer("[{\"airline\":\"AEG\",\"price\":15.23,\"cabinclass\":\"AA\",\"departureAirportCode\":\"HER\",\"destinationAirportCode\":\"AMS\",\"departureDate\":\"2019-12-10\",\"arrivalDate\":\"2019-12-10\"},{\"airline\":\"BA\",\"price\":33.1231,\"cabinclass\":\"BB\",\"departureAirportCode\":\"ATH\",\"destinationAirportCode\":\"LRN\",\"departureDate\":\"2019-12-10\",\"arrivalDate\":\"2019-12-10\"},{\"airline\":\"AA\",\"price\":12.54,\"cabinclass\":\"CC\",\"departureAirportCode\":\"LHT\",\"destinationAirportCode\":\"BCN\",\"departureDate\":\"2020-10-10\",\"arrivalDate\":\"2020-10-10\"},{\"airline\":\"EZY\",\"price\":55.12,\"cabinclass\":\"AA\",\"departureAirportCode\":\"ADL\",\"destinationAirportCode\":\"IND\",\"departureDate\":\"2021-10-10\",\"arrivalDate\":\"2021-10-10\"},{\"airline\":\"EZY\",\"price\":13.12,\"cabinclass\":\"BB\",\"departureAirportCode\":\"HER\",\"destinationAirportCode\":\"AMS\",\"departureDate\":\"2019-12-10\",\"arrivalDate\":\"2019-12-10\"}]");
//        IAirlineRequest airlineRequest = new AirlineRequest();
//        airlineRequest.parseDetails(stringBuffer, "CRAZIE");
//    }
//
//
//    @Test
//    public void normalFeedInputCombinedCheckOrder() {
//
//        // parse Tough
//        StringBuffer stringBufferTough = new StringBuffer("[{\"carrier\":\"AEG\",\"basePrice\":55.65,\"tax\":22.21,\"discount\":1.15,\"departureAirportName\":\"HER\",\"arrivalAirportName\":\"AMS\",\"outboundDateTime\":\"2018-10-10\",\"inboundDateTime\":\"2018-10-10\"},{\"carrier\":\"AEG\",\"basePrice\":222.23,\"tax\":13.12,\"discount\":2.15,\"departureAirportName\":\"ATH\",\"arrivalAirportName\":\"LRN\",\"outboundDateTime\":\"2018-10-10\",\"inboundDateTime\":\"2018-10-10\"},{\"carrier\":\"AEG\",\"basePrice\":35.34,\"tax\":30.2,\"discount\":3.15,\"departureAirportName\":\"BCR\",\"arrivalAirportName\":\"LHT\",\"outboundDateTime\":\"2018-10-10\",\"inboundDateTime\":\"2018-10-10\"},{\"carrier\":\"BB\",\"basePrice\":22.34,\"tax\":19.2,\"discount\":4.15,\"departureAirportName\":\"CC\",\"arrivalAirportName\":\"LHT\",\"outboundDateTime\":\"2018-10-10\",\"inboundDateTime\":\"2018-10-10\"},{\"carrier\":\"AA\",\"basePrice\":128.55,\"tax\":19.2,\"discount\":5.15,\"departureAirportName\":\"CC\",\"arrivalAirportName\":\"JRK\",\"outboundDateTime\":\"2018-10-10\",\"inboundDateTime\":\"2018-10-10\"},{\"carrier\":\"EZY\",\"basePrice\":201.22,\"tax\":30.89,\"discount\":6.15,\"departureAirportName\":\"LGW\",\"arrivalAirportName\":\"JRK\",\"outboundDateTime\":\"2018-10-18\",\"inboundDateTime\":\"2018-10-25\"}]\n");
//        IAirlineRequest airlineRequest = new AirlineRequest();
//
//        //then parse Crazy
//        List<BusyFlightsResponse> busyFlightsResponses = airlineRequest.parseDetails(stringBufferTough, "TOUGH");
//        StringBuffer stringBuffer = new StringBuffer("[{\"airline\":\"AEG\",\"price\":15.23,\"cabinclass\":\"AA\",\"departureAirportCode\":\"HER\",\"destinationAirportCode\":\"AMS\",\"departureDate\":\"2019-12-10\",\"arrivalDate\":\"2019-12-10\"},{\"airline\":\"BA\",\"price\":33.1231,\"cabinclass\":\"BB\",\"departureAirportCode\":\"ATH\",\"destinationAirportCode\":\"LRN\",\"departureDate\":\"2019-12-10\",\"arrivalDate\":\"2019-12-10\"},{\"airline\":\"AA\",\"price\":12.54,\"cabinclass\":\"CC\",\"departureAirportCode\":\"LHT\",\"destinationAirportCode\":\"BCN\",\"departureDate\":\"2020-10-10\",\"arrivalDate\":\"2020-10-10\"},{\"airline\":\"EZY\",\"price\":55.12,\"cabinclass\":\"AA\",\"departureAirportCode\":\"ADL\",\"destinationAirportCode\":\"IND\",\"departureDate\":\"2021-10-10\",\"arrivalDate\":\"2021-10-10\"},{\"airline\":\"EZY\",\"price\":13.12,\"cabinclass\":\"BB\",\"departureAirportCode\":\"HER\",\"destinationAirportCode\":\"AMS\",\"departureDate\":\"2019-12-10\",\"arrivalDate\":\"2019-12-10\"}]");
//        busyFlightsResponses.addAll(airlineRequest.parseDetails(stringBuffer, "CRAZY"));
//
//        //check if we have the number we expect into the list
//        assertEquals(11, busyFlightsResponses.size() );
//
//        //Then
//
//        List<BusyFlightsResponse> busyFlightreturnedList = returnStreamFiltered(busyFlightsResponses);
//
//        //check how many are matching
//     assertEquals(2, busyFlightreturnedList.size());
//
//     //make sure is sorted from smaller to larger.
//     assertEquals(new BigDecimal("13.12"), busyFlightreturnedList.get(0).getFare());
//     assertEquals(new BigDecimal("15.23"), busyFlightreturnedList.get(1).getFare());
//
//    }
//
//    private List<BusyFlightsResponse> returnStreamFiltered(List<BusyFlightsResponse> busyFlightsResponses) {
//        //Assume we have a request that has been sent
//        BusyFlightsRequest request = new BusyFlightsRequest();
//
//        request.setDepartureDate("2019-12-10");
//        request.setDestination("AMS");
//        request.setOrigin("HER");
//        request.setReturnDate("2019-12-10");
//        request.setNumberOfPassengers(2);
//
//        //used into Service class but has to be checked separated in order to make sure it functions
//        return busyFlightsResponses.stream().filter(p -> p.getDepartureAirportCode().equals(request.getOrigin()) && p.getArrivalDate().
//                equals(request.getReturnDate()) && p.getDestinationAirportCode().equals(request.getDestination()) && p.getDepartureDate().equals(request.getDepartureDate()))
//                .sorted(Comparator.comparing(BusyFlightsResponse::getFare)).collect(Collectors.toList());
//    }
//
//
//}
