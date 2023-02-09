package com.blockchainapp.Blockchain.Controllers;

import com.blockchainapp.Blockchain.Models.ExchangeModel;
import com.blockchainapp.Blockchain.Services.CurrencyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TestController {


    @Autowired
    private CurrencyService currencyService;

    @PostMapping("/testing")
    public ResponseEntity<List<Map<String, String>>> deposit() throws JsonProcessingException {
        return new ResponseEntity<List<Map<String, String>>>(currencyService.getCurrencyList(), HttpStatus.OK);
    }

    @PostMapping("/exchange")
    public ResponseEntity<?> getExchangeRate(){
        return new ResponseEntity<>(currencyService.getExchangeRate(), HttpStatus.OK);
    }

    @PostMapping("/convert")
    public ResponseEntity<?> getConversion(){
        return new ResponseEntity<>(currencyService.getConversionRate(), HttpStatus.OK);
    }

}
