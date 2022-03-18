package com.chany.blog.controller;

import com.chany.blog.model.OAuth.KakaoAuthToken;
import com.chany.blog.model.OAuth.KakaoProfile;
import com.chany.blog.model.User;
import com.chany.blog.service.AuthService;
import com.chany.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
//@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AuthService authService;
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

        // kakao auth token 가져오기
        KakaoAuthToken kakaoAuthToken = authService.getKakaoAuthToken(code);

        // 사용자 정보 가져오기
        KakaoProfile profile = authService.getKakaoProfile(kakaoAuthToken.getAccess_token());

        // 사용자 확인 및 회원가입
        User kakaoUser = authService.getKakaoAuthUser(profile);

        // 로그인 처리
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), key
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/";
    }
}
