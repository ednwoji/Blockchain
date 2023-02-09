package com.blockchainapp.Blockchain.Services;

import com.blockchainapp.Blockchain.Models.ExchangeModel;
import com.blockchainapp.Blockchain.Models.WalletExchange;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    @Override
    public List<Map<String, String>> getCurrencyList() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-api-key", "Rs0Pdkgvk");
        ObjectMapper objectMapper = new ObjectMapper();

        String url = "https://api.swapzone.io/v1/exchange/currencies?=";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<String> currenciesResponse = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        @SuppressWarnings("unchecked")
        List<Map<String,String>> list = objectMapper.readValue(currenciesResponse.getBody(), List.class);
        for (Map<String, String> listing: list){
            if (listing.get("ticker").equals("btc"))
                System.out.println(listing);

            if (listing.get("ticker").equals("eth"))
                System.out.println(listing.get("name"));


        }


//        for (Map<String, String> listing: list1){
//            if (listing.get("ticker").equals("btc"))
//                System.out.println(listing);
//
//            if (listing.get("ticker").equals("eth"))
//                System.out.println(listing.get("name"));
//
//
//        }





//        System.out.println(list.get(10).get("name"));
        return list;

//        CurrenciesResponse currenciesResponse1 = objectMapper.readValue(currenciesResponse.getBody(), CurrenciesResponse.class);
//        return currenciesResponse1;
    }

    @Override
    public ResponseEntity<ExchangeModel> getExchangeRate() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("apikey", "J7nXXOalB1aB6ItGBbWeyYwGfJBtGC1i");
        ObjectMapper objectMapper = new ObjectMapper();

        String url1 = "https://api.apilayer.com/exchangerates_data/convert?to=ngn&from=usd&amount=1";

        RestTemplate restTemplate1 = new RestTemplate();
        HttpEntity<?> entity1 = new HttpEntity<>(httpHeaders);
        ResponseEntity<ExchangeModel> currenciesResponse1 = restTemplate1.exchange(url1, HttpMethod.GET, entity1, ExchangeModel.class);

        System.out.println(currenciesResponse1.getBody().getResult());
//        @SuppressWarnings("unchecked")
//        List<Map<String,String>> list1 = objectMapper.readValue(currenciesResponse1.getBody(), List.class);
        return currenciesResponse1;

    }

    @Override
    public  ResponseEntity<WalletExchange> getConversionRate() {
        HttpHeaders httpHeaders2 = new HttpHeaders();
        httpHeaders2.add("x-api-key", "Rs0Pdkgvk");

        ObjectMapper objectMapper2 = new ObjectMapper();

        String url2 = "https://api.swapzone.io/v1/exchange/get-rate?from=usdt&to=btc&amount=1000&rateType=all&availableInUSA=false&chooseRate=best&noRefundAddress=false";

        RestTemplate restTemplate2 = new RestTemplate();
        HttpEntity<?> entity2 = new HttpEntity<>(httpHeaders2);
        ResponseEntity<WalletExchange> currenciesResponse2 = restTemplate2.exchange(url2, HttpMethod.GET, entity2, WalletExchange.class);

        System.out.println(currenciesResponse2.getBody().getAmountTo());
//        @SuppressWarnings("unchecked")
//        List<Map<String,String>> list1 = objectMapper.readValue(currenciesResponse1.getBody(), List.class);
        return currenciesResponse2;

    }
}
