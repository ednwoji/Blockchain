package com.blockchainapp.Blockchain.Controllers;

import com.blockchainapp.Blockchain.Models.*;
import com.blockchainapp.Blockchain.Repository.AccountRepository;
import com.blockchainapp.Blockchain.Repository.PaymentHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/app")
@Slf4j
public class AppController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;

    @Value("${interTransferCharges}")
    private int charges;


    @GetMapping("/dashboard")
    public ModelAndView getDashboard(HttpSession session) {
        ModelAndView getDashboardPage = new ModelAndView("dashboard");

        Users users = (Users)session.getAttribute("user");
        List<Accounts> getAccounts = accountRepository.getUserAccountsById(users.getUser_id());
        BigDecimal totalAccountBalance = accountRepository.getTotalBalance(users.getUser_id());

        RestTemplate restTemplate = new RestTemplate();
        String urls = "https://min-api.cryptocompare.com/data/price?fsym=USD&tsyms=NGN";
        EthToDollar amount = restTemplate.getForEntity(urls, EthToDollar.class).getBody();
        String unitRateInNaira = amount.getNGN();



        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer sk_test_d86d7aaa7e22a0aa317efe79441c44d93da1d7fc");

        String url = "https://api.paystack.co/bank?currency=NGN";

        RestTemplate restTemplates = new RestTemplate();
        HttpEntity<?> entity = new HttpEntity<>(headers);
        RootPayStack BanksResponse = restTemplate.exchange(url, HttpMethod.GET, entity, RootPayStack.class).getBody();



        getDashboardPage.addObject("userAccounts", getAccounts);
        getDashboardPage.addObject("totalBalance", totalAccountBalance);
        getDashboardPage.addObject("dollarRate", unitRateInNaira);
        getDashboardPage.addObject("bankList", BanksResponse.getData());
        getDashboardPage.addObject("charges", charges);




        return getDashboardPage;


    }


    @PostMapping("/getCustomer")
    public ResponseEntity<Object> getAllCustomer(@RequestBody JqueryObject jqueryObject) {

        try {
            List<Accounts> accounts = accountRepository.getAccountName(jqueryObject.getDeposit_account());

            if (accounts != null) {
                ServiceResponse response = new ServiceResponse("success", accounts.get(0).getAccount_name());
                return new ResponseEntity<Object>(response, HttpStatus.OK);

            } else if (accounts == null) {
                ServiceResponse response = new ServiceResponse("failed", "The account number does not exist");
                return new ResponseEntity<Object>(response, HttpStatus.OK);

            }
        }
        catch(Exception ex) {

            ServiceResponse response = new ServiceResponse("failed", "The account number does not exist");
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }

        ServiceResponse response = new ServiceResponse("failed", "The account number does not exist");
        return new ResponseEntity<Object>(response, HttpStatus.OK);

    }


    @PostMapping("/getRecipient")
    public ResponseEntity<Object> getAllBanks(@RequestBody JqueryObject jqueryObject) {

        System.out.println(jqueryObject.getBankCode());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer sk_test_d86d7aaa7e22a0aa317efe79441c44d93da1d7fc");

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> entity = new HttpEntity<>(headers);



            String url = "https://api.paystack.co/bank/resolve?account_number=" + jqueryObject.getAccountNumber() + "&bank_code=" + jqueryObject.getBankCode();

            RootAccountValidationPayStack BanksResponse = restTemplate.exchange(url, HttpMethod.GET, entity, RootAccountValidationPayStack.class).getBody();
            return new ResponseEntity<Object>(BanksResponse, HttpStatus.OK);



    }

    @GetMapping("/history")
    public ModelAndView getPaymentHistory(HttpSession session){
        ModelAndView getHistory = new ModelAndView("paymentHistory");
        Users user = (Users) session.getAttribute("user");
        List<PaymentHistory> userPaymentHistory = paymentHistoryRepository.getPaymentRecordsById(user.getUser_id());
        System.out.println(userPaymentHistory);

        getHistory.addObject("payment_history",userPaymentHistory );
        return getHistory;
    }



}
