package com.chany.blog.service;

import com.chany.blog.model.OAuth.KakaoAuthToken;
import com.chany.blog.model.OAuth.KakaoProfile;
import com.chany.blog.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;

    @Value("${chany.key}")
    private String key;

    @Value("${chany.kakao.auth-redirect-url}")
    private String authRedirectUrl;

    @Value("${chany.kakao.auth-request-url}")
    private String authRequestUrl;

    @Value("${chany.kakao.client-id}")
    private String authClientId;

    @Value("${chany.kakao.profile-request-url}")
    private String profileRequestUrl;

    public KakaoAuthToken getKakaoAuthToken(String code) {
        RestTemplate rt = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", authClientId);
        params.add("redirect_uri", authRedirectUrl);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, header);
        ResponseEntity<String> response = rt.exchange(
                authRequestUrl,
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );
        KakaoAuthToken kakaoAuthToken = null;

        try {
            kakaoAuthToken = objectMapper.readValue(response.getBody(), KakaoAuthToken.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return kakaoAuthToken;
    }

    public KakaoProfile getKakaoProfile(String kakaoAcessToekn) {
        ObjectMapper objectMapper = new ObjectMapper();
        RestTemplate rt = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        header.setBearerAuth(kakaoAcessToekn);

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(header);
        ResponseEntity<String> response = rt.exchange(
                profileRequestUrl,
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        KakaoProfile profile = null;
        try {
            profile = objectMapper.readValue(response.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return profile;
    }

    public User getKakaoAuthUser(KakaoProfile profile) {

        String username = profile.getKakao_account().getEmail() + "_" + profile.getId();
        String email = profile.getKakao_account().getEmail();

        User kakaoUser = userService.find(username);

        if (kakaoUser == null) {
            kakaoUser = userService.saveFromKakao(username, email, key);
        }

        return kakaoUser;
    }
}
