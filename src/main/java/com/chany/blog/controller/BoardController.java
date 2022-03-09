package com.chany.blog.controller;

import com.chany.blog.config.auth.PrincipalDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    // @AuthenticationPrincipal PrincipalDetail principal
    @GetMapping({"", "/"})
    public String index() {
//        System.out.println("로그인 사용자 : " + principal.getUsername());
        return "index";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }
}
