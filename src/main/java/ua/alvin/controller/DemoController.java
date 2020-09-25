package ua.alvin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String landingPage() {
        return "landing";
    }

    @GetMapping("/employees")
    public String homePage(){
        return "home";
    }

    @GetMapping("/leaders")
    public String leadersPage(){
        return "leaders";
    }

    @GetMapping("/systems")
    public String adminsPage(){
        return "admins";
    }

}
