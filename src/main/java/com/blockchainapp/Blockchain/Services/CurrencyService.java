package com.blockchainapp.Blockchain.Services;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

public interface CurrencyService {
    List<Map<String, String>> getCurrencyList() throws JsonProcessingException;

    Object getExchangeRate();

    Object getConversionRate();
}
