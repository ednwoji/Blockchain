package com.blockchainapp.Blockchain.Controllers;

import com.blockchainapp.Blockchain.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.bind.SchemaOutputResolver;

@Controller
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/")
    public ModelAndView indexPage() {

        ModelAndView homepage = new ModelAndView("index");
        homepage.addObject("Welcome", "Hello World");
        return homepage;
    }



    @GetMapping("/register")
    public ModelAndView getRegister() {

        ModelAndView registerPage = new ModelAndView("register");
        registerPage.addObject("pageTitle", "Register");
        return registerPage;
    }

    @GetMapping("/error")
    public ModelAndView getError() {

        ModelAndView registerPage = new ModelAndView("error");
        registerPage.addObject("pageTitle", "Error");
        return registerPage;
    }

    @GetMapping("/verify")
    public ModelAndView getVerify(@RequestParam("token") String token, @RequestParam("code") String code) {

        ModelAndView VerifyPage = new ModelAndView("login");
        String dbToken = userRepository.checkToken(token);
        if(dbToken == null)
        {
            VerifyPage.addObject("error", "This session has expired");
            return VerifyPage;

        }
        userRepository.verifyAccount(token, code);

        VerifyPage.addObject("success", "Account verified Successfully, please proceed to login");
        return VerifyPage;
    }

}
