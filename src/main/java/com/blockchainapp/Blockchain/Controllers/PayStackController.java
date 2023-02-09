package com.blockchainapp.Blockchain.Controllers;


import com.blockchainapp.Blockchain.Models.DatumPayStack;
import com.blockchainapp.Blockchain.Models.JqueryObject;
import com.blockchainapp.Blockchain.Models.RootAccountValidationPayStack;
import com.blockchainapp.Blockchain.Models.RootPayStack;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/bank")
public class PayStackController {

    @PostMapping("/getRecipient")
    public ResponseEntity<Object> getAllBanks(@RequestBody JqueryObject jqueryObject) {


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer sk_test_d86d7aaa7e22a0aa317efe79441c44d93da1d7fc");

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> entity = new HttpEntity<>(headers);

        String url = "https://api.paystack.co/bank/resolve?account_number="+jqueryObject.getAccountNumber()+"&bank_code"+jqueryObject.getBankCode();

        RootAccountValidationPayStack BanksResponse = restTemplate.exchange(url, HttpMethod.GET, entity, RootAccountValidationPayStack.class).getBody();


        return new ResponseEntity<Object>(BanksResponse, HttpStatus.OK);



    }

}