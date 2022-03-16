package com.chany.blog.controller;

import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


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

    @GetMapping("/user/updateForm")
    public String updateForm() {

        return "/user/updateForm";
    }

    @GetMapping("/auth/kakao/callback")
    public @ResponseBody String kakaoCallback(String code) {

        RestTemplate rt = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "0b574c03d54a84e92c553163fef5b367");
        params.add("redirect_uri", "http://localhost:28088/auth/kakao/callback");
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, header);
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );
        return "카카오 인증 완료 : " + response;
    }
}
