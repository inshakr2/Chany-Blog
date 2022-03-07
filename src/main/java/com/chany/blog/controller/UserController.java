package com.chany.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
//@RequestMapping("/user")
public class UserController {

    @GetMapping("/auth/joinForm")
    public String joinForm() {

        return "/user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm() {

        return "/user/loginForm";
    }
}
