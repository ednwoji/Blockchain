package com.blockchainapp.Blockchain.Controllers;

import com.blockchainapp.Blockchain.ConverterEntities.Root;
import com.blockchainapp.Blockchain.Models.*;
import com.blockchainapp.Blockchain.Repository.AccountRepository;
import com.blockchainapp.Blockchain.Repository.PaymentRepository;
import com.blockchainapp.Blockchain.Services.CurrencyService;
import com.blockchainapp.Blockchain.Services.MyService;
import com.blockchainapp.Blockchain.helpers.Token;
import org.bitcoin.protocols.payments.Protos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Properties;


import org.bitcoinj.core.*;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.bitcoinj.wallet.Wallet;
import org.bitcoinj.wallet.DeterministicSeed;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Controller
@RequestMapping("/transact")
public class TransactController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private MyService myService;

    @Autowired
    private CurrencyService currencyService;

    @Value("${seedCode}")
    private String seedCode;

    @Value("${ethWalletPassword}")
    private String password;

    @Value("${interTransferCharges}")
    private int interCharges;

    @Value("${otherBanksTransferCharges}")
    private int otherCharges;

    @Value("${coinbaseConverter}")
    private String converter;

    LocalDateTime currentDateTime = LocalDateTime.now();


    @PostMapping("/deposit")
    public String depositFunds(@RequestParam("deposit_amount") String depositAmount,
                                  @RequestParam("account_id") String accountID,
                                  @RequestParam("wallet-type") String walletType,
                                  payments paymentss,
                                  HttpSession session,
                                  RedirectAttributes redirectAttributes) throws IOException, UnreadableWalletException, InterruptedException, InvalidAlgorithmParameterException, CipherException, NoSuchAlgorithmException, NoSuchProviderException {

        if(accountID == null){
            redirectAttributes.addFlashAttribute("error", "Account Type should be selected");
            return "redirect:/app/dashboard";
        }

        if(walletType == null){
            redirectAttributes.addFlashAttribute("error", "Wallet Type should be selected");
            return "redirect:/app/dashboard";
        }

        Users users = (Users)session.getAttribute("user");

        int acc_id = Integer.parseInt(accountID);

        RestTemplate restTemplate = new RestTemplate();
        Root root = new Root();
        root = restTemplate.getForEntity(converter, Root.class).getBody();

//        RestTemplate restTemplate1 = new RestTemplate();
//        String tstUrl = "http://127.0.0.1:3000/api/v2/create?password=Agbado@123&api_code=5fe4dbe3-d9c5-4f8e-889c-a1dd5bd73a86&label=YOUR-LABEL-OPTIONAL&hd=true";
//        ResponseEntity<BlockchainResponse> blockchainResponse = restTemplate1.postForEntity(tstUrl,null, BlockchainResponse.class);

//        String unitRateinNaira = root.getData().getRates().getNGN();
//        double usdAmount = Double.parseDouble(depositAmount) / Double.parseDouble(unitRateinNaira);

//        String url = "https://blockchain.info/tobtc?currency=USD&value=" + usdAmount;
//        String convertedAmount = restTemplate.getForEntity(url, String.class).getBody();



        if(walletType.equals("btc")) {

            String urls = "https://min-api.cryptocompare.com/data/price?fsym=NGN&tsyms=BTC";
            EthToDollar amount = restTemplate.getForEntity(urls, EthToDollar.class).getBody();
            BigDecimal convertedAmount = amount.getBTC().multiply(BigDecimal.valueOf(Long.parseLong(depositAmount)));


            NetworkParameters params = MainNetParams.get();

            Wallet wallet = Wallet.fromSeed(
                    NetworkParameters.fromID(NetworkParameters.ID_MAINNET),
                    new DeterministicSeed(seedCode, null, "", 0L)
            );

            Address freshAddress = wallet.freshReceiveAddress();

            myService.doSomething(wallet, freshAddress, Double.parseDouble(depositAmount), acc_id, convertedAmount,
                    "Self", "Self", "BtcTopUp"+Token.generateReference(), "Under Review", "Topup Wallet");

            String qrcodeApi = "https://chart.googleapis.com/chart?chs=225x225&chld=L|2&cht=qr&chl="+freshAddress;
            ResponseEntity<byte[]> qrcode = restTemplate.exchange(
                    qrcodeApi, HttpMethod.GET, null, byte[].class);
            byte[] image = qrcode.getBody();

            redirectAttributes.addFlashAttribute("convertedAmount", convertedAmount);
            redirectAttributes.addFlashAttribute("transAddress", freshAddress);
            redirectAttributes.addFlashAttribute("charges", interCharges);
            redirectAttributes.addFlashAttribute("qrcode", "data:image/png;base64," + Base64.getEncoder().encodeToString(image));
            return "redirect:/app/dashboard";

        }

        else if(walletType.equals("eth")){

            Web3j web3j = Web3j.build(new HttpService("https://mainnet.infura.io/v3/08e485165f734312abf740262e32f76f"));  // connect to an Ethereum node
            String walletFileName = WalletUtils.generateFullNewWalletFile(password, new File("C:\\EthWallets"));

            Credentials credentials = WalletUtils.loadCredentials(password, "C:\\EthWallets\\" + walletFileName);
//            System.out.println("Private key: " + credentials.getEcKeyPair().getPrivateKey().toString(16));

            String urls = "https://min-api.cryptocompare.com/data/price?fsym=NGN&tsyms=ETH";
            EthToDollar amount = restTemplate.getForEntity(urls, EthToDollar.class).getBody();
            BigDecimal ethConvert = amount.getETH().multiply(BigDecimal.valueOf(Long.parseLong(depositAmount)));


            myService.ethWallet(web3j,credentials.getAddress(), ethConvert,Double.parseDouble(depositAmount), acc_id,
                    "Self", "Self", "EthTopUp"+Token.generateReference(), "Under Review", "Topup Wallet");

            String qrcodeApi = "https://chart.googleapis.com/chart?chs=225x225&chld=L|2&cht=qr&chl="+credentials.getAddress();
            ResponseEntity<byte[]> qrcode = restTemplate.exchange(
                    qrcodeApi, HttpMethod.GET, null, byte[].class);
            byte[] image = qrcode.getBody();


            redirectAttributes.addFlashAttribute("ethConvertedAmount", ethConvert);
            redirectAttributes.addFlashAttribute("ethTransAddress", credentials.getAddress());
            redirectAttributes.addFlashAttribute("qrcode", "data:image/png;base64," + Base64.getEncoder().encodeToString(image));

            return "redirect:/app/dashboard";
        }
        return "redirect:/app/dashboard";

    }



    @PostMapping("/transfer")
    public String deposit(@RequestParam("deposit_amount") String depositAmount,
                          @RequestParam("deposit_account") String depositAccount,
                          @RequestParam("beneficiary") String beneficiaryName,
                          @RequestParam("account_id") String accountID,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {

        if(accountID == null){
            redirectAttributes.addFlashAttribute("error", "Account Type should be selected");
            return "redirect:/app/dashboard";
        }


        Users users = (Users)session.getAttribute("user");

        int acc_id = Integer.parseInt(accountID);
        double depositAmountValue = Double.parseDouble(depositAmount) + interCharges;
        double depositAmountValueWithoutCharges = Double.parseDouble(depositAmount);
        double currentBalance = accountRepository.getAccountBalance(depositAccount);
        double userBalance = accountRepository.UserAccountBalance(users.getUser_id(), acc_id);

        if(depositAmountValue <= 0){

            redirectAttributes.addFlashAttribute("error", "Transfer Amount must be greater than zero");
            return "redirect:/app/dashboard";
        }

        if(userBalance < depositAmountValue){

            redirectAttributes.addFlashAttribute("error", "Your Account Balance is insufficient for this transfer");
            return "redirect:/app/dashboard";
        }

        double newBalance = currentBalance + depositAmountValueWithoutCharges;
        accountRepository.ChangeAccountBalance(newBalance, depositAccount);

        double newUserBalance = userBalance - depositAmountValue;
        accountRepository.NewAccountBalance(newUserBalance, acc_id);

        payments paymentss = new payments();
        paymentss.setAccount_id(acc_id);
        paymentss.setBeneficiary(beneficiaryName);
        paymentss.setBeneficiary_acc_no(depositAccount);
        paymentss.setAmount(Double.parseDouble(depositAmount));
        paymentss.setReference_no("Transfer"+Token.generateReference());
        paymentss.setStatus("Successful");
        paymentss.setReason_code("Transfer");


        paymentRepository.save(paymentss);


        redirectAttributes.addFlashAttribute("success", "Amount Successfully Sent");
        return "redirect:/app/dashboard";

    }


    @PostMapping("/payment")
    public String billPayment(@RequestParam("number") String billNumber,
                          @RequestParam("billType") String billType,
                          @RequestParam("account_id") String accountID,
                          @RequestParam("amount") int amount,
                          @RequestParam("serviceProvider") String serviceProvider,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {

        if(!billType.equals("electricity"))
        {
          String url =  "https://vtu.com.ng/API/VTU/?api_key=f41228c3a23af443745b7e0060878b&network="+serviceProvider+"&phone="+billNumber+"&amount="+amount;

          RestTemplate restTemplate = new RestTemplate();
          String response = restTemplate.getForEntity(url, String.class).getBody();

            redirectAttributes.addFlashAttribute("success", response);
            return "redirect:/app/dashboard";

        }

        return "redirect:/app/dashboard";
    }


    @PostMapping("/send")
    public String MoveFunds(@RequestParam("withdraw_amount") String transferAmount,
                          @RequestParam("transferAccount") String transferAccount,
                          @RequestParam("transferName") String transferName,
                          @RequestParam("account_id") String accountID,
                          @RequestParam("bankList") String bankName,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("tst", "We're Testing");

        payments paymentss = new payments();
        paymentss.setAccount_id(Integer.parseInt(accountID));
        paymentss.setBeneficiary(transferName);
        paymentss.setBeneficiary_acc_no(transferAccount);
        paymentss.setAmount(Double.parseDouble(transferAmount));
        paymentss.setReference_no("Transfer"+Token.generateReference());
        paymentss.setStatus("Successful");
        paymentss.setReason_code("Transfer");

        paymentRepository.save(paymentss);


        return "redirect:/app/dashboard";

    }

    @PostMapping("/payments")
    public String payBills(@RequestParam("amount") String amount,
                            @RequestParam("billType") String billType,
                            @RequestParam("number") String number,
                            @RequestParam("account_id") String accountID,
                            @RequestParam("serviceProvider") String serviceProvider,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("tst", "We're Testing");

        payments paymentss = new payments();
        paymentss.setAccount_id(Integer.parseInt(accountID));
        paymentss.setBeneficiary(serviceProvider);
        paymentss.setBeneficiary_acc_no(serviceProvider);
        paymentss.setAmount(Double.parseDouble(amount));
        paymentss.setReference_no(billType+Token.generateReference());
        paymentss.setStatus("Successful");
        paymentss.setReason_code(billType);

        paymentRepository.save(paymentss);


        return "redirect:/app/dashboard";

    }



    }
