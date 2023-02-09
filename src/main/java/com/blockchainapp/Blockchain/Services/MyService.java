package com.blockchainapp.Blockchain.Services;

import com.blockchainapp.Blockchain.Models.payments;
import com.blockchainapp.Blockchain.Repository.AccountRepository;
import com.blockchainapp.Blockchain.Repository.PaymentRepository;
import com.google.common.hash.Hashing;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.bitcoinj.wallet.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@Service
public class MyService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PaymentRepository paymentRepository;
    private final TaskExecutor taskExecutor;

    public MyService(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public void doSomething(Wallet wallet, Address freshAddress, double deposit, int account_id, BigDecimal btcAmount,
                            String beneficiary, String beneficiaryAcc, String reference,
                            String status, String reasonCode) throws UnreadableWalletException, InterruptedException {

        String hashAddress = Hashing.sha256()
                .hashString(freshAddress.toString(), StandardCharsets.UTF_8)
                .toString();

        taskExecutor.execute(() -> {

            Transaction tx = wallet.getTransaction(Sha256Hash.wrap(hashAddress));
            while(tx==null)
            {

                if(tx != null){
                    Coin amountReceived = tx.getValue(wallet);
                    Coin amountGotten = wallet.getBalance();

                    System.out.println("Received "+amountReceived.toPlainString() + " BTC");
                    if(btcAmount.equals(amountReceived.toPlainString())) {
                        accountRepository.ModifyAccountBalance(deposit, account_id);

                        payments paymentss = new payments();
                        paymentss.setAccount_id(account_id);
                        paymentss.setBeneficiary(beneficiary);
                        paymentss.setAmount(deposit);
                        paymentss.setStatus(status);
                        paymentss.setReason_code(reasonCode);
                        paymentss.setReference_no(reference);
                        paymentss.setBeneficiary_acc_no(beneficiaryAcc);

                        paymentRepository.save(paymentss);

                    }
                    break;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        });
    }

    public void ethWallet(Web3j web3j, String walletAddress, BigDecimal ethAmount, double deposit, int account_id,
                          String beneficiary, String beneficiaryAcc, String reference,
                          String status, String reasonCode)  {


        taskExecutor.execute(() -> {

            BigInteger previousBalance = BigInteger.ZERO;  // the previous balance of the wallet

            EthGetBalance balance;
            try {
                balance = web3j.ethGetBalance(walletAddress, DefaultBlockParameterName.LATEST).send();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            BigInteger currentBalance = balance.getBalance();

            while (Integer.parseInt(String.valueOf(currentBalance)) == 0) {

                try {
                    balance = web3j.ethGetBalance(walletAddress, DefaultBlockParameterName.LATEST).send();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                currentBalance = balance.getBalance();


                if (currentBalance.compareTo(previousBalance) > 0) {
                    // the balance has increased, funds have been deposited
                    BigDecimal increaseInEther = Convert.fromWei(currentBalance.subtract(previousBalance).toString(), Convert.Unit.ETHER);
                    System.out.println("Funds deposited: " + increaseInEther + " Ether");
                    if(ethAmount.equals(increaseInEther)){
                        accountRepository.ModifyAccountBalance(deposit, account_id);

                        payments paymentss = new payments();
                        paymentss.setAccount_id(account_id);
                        paymentss.setBeneficiary(beneficiary);
                        paymentss.setAmount(deposit);
                        paymentss.setStatus(status);
                        paymentss.setReason_code(reasonCode);
                        paymentss.setReference_no(reference);
                        paymentss.setBeneficiary_acc_no(beneficiaryAcc);

                        paymentRepository.save(paymentss);

                    }
                    break;
                }


                // wait for a period of time before checking the balance again
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        });
    }
}

