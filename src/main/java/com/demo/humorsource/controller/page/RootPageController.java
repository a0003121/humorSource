package com.demo.humorsource.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootPageController {

    // Login form
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    // Login form  
    @RequestMapping("/login-error")
    public String loginError() {
        return "login-error";
    }

    @RequestMapping("/user_index")
    public String userIndex() {
        return "/user/index";
    }
}
