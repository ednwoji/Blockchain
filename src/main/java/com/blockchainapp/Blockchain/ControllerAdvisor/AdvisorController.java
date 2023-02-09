package com.blockchainapp.Blockchain.ControllerAdvisor;

import com.blockchainapp.Blockchain.Models.Users;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class AdvisorController {

    @ModelAttribute("registerUser")
    public Users getUserDefaults() {

        return new Users();
    }
}
