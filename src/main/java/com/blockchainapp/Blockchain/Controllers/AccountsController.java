package com.blockchainapp.Blockchain.Controllers;

import com.blockchainapp.Blockchain.Models.Users;
import com.blockchainapp.Blockchain.Repository.AccountRepository;
import com.blockchainapp.Blockchain.helpers.GenAccountNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/account")
public class AccountsController {

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/create_account")
    public String createAccount(@RequestParam("account_name") String accountName,
                                @RequestParam("account_type") String accountType,
                                RedirectAttributes redirectAttributes,
                                HttpSession session){

        if(accountType.isEmpty()){
            redirectAttributes.addFlashAttribute("error", "Select an account type");
            return "redirect:/app/dashboard";


        }
        Users user = (Users)session.getAttribute("user");

        int setAccountNumber = GenAccountNumber.generateAccountNumber();
        String bankAccountNumber = Integer.toString(setAccountNumber);
        accountRepository.createBankAccount(user.getUser_id(), bankAccountNumber, accountName,accountType);
        redirectAttributes.addFlashAttribute("success", "Account created Successfully");
        return "redirect:/app/dashboard";
    }
}
