package ua.alvin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {


    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage(){

//        return "plain-login";
        return "fancy-login";
    }

    @GetMapping("/access-denied")
    public String accessDeniedPage(){

        return "access-denied";
    }
}
