package com.blockchainapp.Blockchain.Controllers;


import com.blockchainapp.Blockchain.Models.Users;
import com.blockchainapp.Blockchain.Repository.UserRepository;
import com.blockchainapp.Blockchain.helpers.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public ModelAndView getLogin() {

        ModelAndView loginPage = new ModelAndView("login");
        String token = Token.generateToken();
        loginPage.addObject("token", token);
        loginPage.addObject("pageTitle", "Login");
        return loginPage;
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        @RequestParam("_token") String token, Model model,
                        HttpSession session) {

        // TODO: VALIDATE INPUT FIELDS/FORM DATA:
        if(email.isEmpty() || email == null || password.isEmpty() || password == null){
            model.addAttribute("error", "Username or passsword cannot be empty");
            return "login";
        }
        // TODO: CHECK IF EMAIL EXISTS:
        String getEmailInDatabase = userRepository.getUserEmail(email);
       if(getEmailInDatabase != null){
           String getPasswordInDatabase = userRepository.getUserPassword(getEmailInDatabase);

           if(!BCrypt.checkpw(password, getPasswordInDatabase)){
               model.addAttribute("error", "Incorrect Username or Password");
               return "login";
           }

           else {
               int verified = userRepository.isVerified(getEmailInDatabase);

               if (verified != 1) {
                   model.addAttribute("error", "Your account is not yet verified, check email and verify account");
                    return "login";
               }

               Users users = userRepository.getUserDetails(getEmailInDatabase);

               session.setAttribute("user", users);
               session.setAttribute("token", token);
               session.setAttribute("authenticated", true);

               return "redirect:/app/dashboard";

           }

       }
       else{
           model.addAttribute("wrongEmail", "Email Address entered is incorrect");
           return "login";

       }

        // TODO: CHECK IF USER ACCOUNT IS VERIFIED:


        //TODO: PROCEED TO LOG THE USER IN:


    }

    @GetMapping ("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes){
        session.invalidate();
        redirectAttributes.addFlashAttribute("logged_out", "Logged out Successfully");

        return "redirect:/login";
    }
}
