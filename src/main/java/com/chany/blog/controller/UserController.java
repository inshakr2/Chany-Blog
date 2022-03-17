package com.chany.blog.controller;

import com.chany.blog.model.OAuth.KakaoAuthToken;
import com.chany.blog.model.OAuth.KakaoProfile;
import com.chany.blog.model.User;
import com.chany.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;


@Controller
@RequiredArgsConstructor
//@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager manager;

    @Value("${chany.key}")
    private String key;

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
    public String kakaoCallback(String code) {

        // 엑세스 토큰 받기
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
        KakaoAuthToken kakaoAuthToken = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            kakaoAuthToken = objectMapper.readValue(response.getBody(), KakaoAuthToken.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // 사용자 정보 가져오기
        RestTemplate rt2 = new RestTemplate();
        HttpHeaders header2 = new HttpHeaders();
        header2.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        header2.setBearerAuth(kakaoAuthToken.getAccess_token());

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(header2);
        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        KakaoProfile profile = null;
        try {
            profile = objectMapper.readValue(response2.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String username = profile.getKakao_account().getEmail() + "_" + profile.getId();
        String email = profile.getKakao_account().getEmail();

        User kakaoUser = userService.find(username);

        if (kakaoUser == null) {
            kakaoUser = userService.saveFromKakao(username, email, key);
        }

        // 로그인 처리
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), key
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/";
    }
}
