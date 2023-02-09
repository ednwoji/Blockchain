package com.blockchainapp.Blockchain.Controllers;


import com.blockchainapp.Blockchain.Models.Users;
import com.blockchainapp.Blockchain.Repository.UserRepository;
import com.blockchainapp.Blockchain.helpers.HTML;
import com.blockchainapp.Blockchain.helpers.Token;
import com.blockchainapp.Blockchain.mailMessenger.MailMessenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.Random;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute("registerUser") Users user, BindingResult result,
                                 @RequestParam("first_name") String first_name,
                                 @RequestParam("last_name") String last_name,
                                 @RequestParam("email") String email,
                                 @RequestParam("password") String password,
                                 @RequestParam("confirm_password") String confirm_password) throws MessagingException {

        ModelAndView registrationPage = new ModelAndView("register");

        if(result.hasErrors() && confirm_password.isEmpty()) {

            registrationPage.addObject("confirm_pass", "The confirm field is required");
            return registrationPage;
        }

        if(!password.equals(confirm_password)) {
            registrationPage.addObject("passwordMismatch", "Password Mismatch");
            return registrationPage;
        }

        //TODO: CHECK FOR PASSWORD MATCH:
        //TODO: GET TOKEN STRING:
        String token = Token.generateToken();

        //TODO: GENERATE RANDOM CODE:
        Random rand = new Random();
        int bound = 123;
        int code = bound * rand.nextInt(bound);

        //TODO: GET Email HTML BODY:
        String emailBody = HTML.htmlEmailTemplate(token, String.valueOf(code));

        //TODO: HASH PASSWORD:
        String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());

        //TODO: SEND EMAIL NOTIFICATION:
        MailMessenger.htmlEmailMessenger("emeka@check.tl", email, "Verify Account", emailBody);


        //TODO: REGISTER USER:
        Users users = new Users();
        users.setPassword(hashed_password);
        users.setCode(String.valueOf(code));
        users.setFirst_name(first_name);
        users.setEmail(email);
        users.setLast_name(last_name);
        users.setToken(token);
        userRepository.save(users);
//        userRepository.registerUser(first_name, last_name, email, hashed_password,token, String.valueOf(code));
        //TODO: RETURN TO REGISTER PAGE
        String successMessage = "Account Registered Successfully, Please check your Email and verify Account";
        registrationPage.addObject("Success", successMessage);

        return registrationPage;
    }

}
