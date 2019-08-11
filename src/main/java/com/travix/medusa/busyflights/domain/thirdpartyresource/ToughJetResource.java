package com.travix.medusa.busyflights.domain.thirdpartyresource;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//assuming this is a third party api
@RestController
@RequestMapping("/toughJet")
public class ToughJetResource {
//third party api it can return data from ToughJet

    @RequestMapping(method = RequestMethod.GET, path = "toughJetDataFetch")
    public List<ToughJetResponse> getToughBudjetFlights(ToughJetRequest request) {
        List<ToughJetResponse> flights = new ArrayList<>();
        flights.add(new ToughJetResponse("AEG", new BigDecimal("55.65"), new BigDecimal("22.21"),new BigDecimal( "1.15"),  "HER", "AMS", "2018-10-10", "2018-10-10"));
        flights.add(new ToughJetResponse("AEG", new BigDecimal("222.23"), new BigDecimal("13.12"),new BigDecimal( "2.15"), "ATH", "LRN", "2018-10-10", "2018-10-10"));
        flights.add(new ToughJetResponse("AEG", new BigDecimal("35.34"),new BigDecimal("30.2"),new BigDecimal( "3.15"),  "BCR", "LHT","2018-10-10", "2018-10-10"));
        flights.add(new ToughJetResponse("BB", new BigDecimal("22.34"), new BigDecimal("19.2"),new BigDecimal( "4.15"),  "CC", "LHT","2018-10-10", "2018-10-10"));
        flights.add(new ToughJetResponse("AA", new BigDecimal("128.55"), new BigDecimal("19.2"),new BigDecimal( "5.15"), "CC", "JRK","2018-10-10", "2018-10-10"));
        flights.add(new ToughJetResponse("EZY", new BigDecimal("201.22"), new BigDecimal("30.89"),new BigDecimal( "6.15"), "LGW", "JRK", "2018-10-18", "2018-10-25"));

     return flights;
    }

}
