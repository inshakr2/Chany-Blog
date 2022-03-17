package com.chany.blog.service;

import com.chany.blog.model.AuthType;
import com.chany.blog.model.User;
import com.chany.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.MethodNotAllowedException;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public Integer save(User user) {

        String hashPwd = encoder.encode(user.getPassword());
        user.setPassword(hashPwd);

        try {
            userRepository.save(user);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR UserService.save :" + e.getMessage());
        }
        return -1;
    }


    public User saveFromKakao(String username, String email, String password) {

        String hashPwd = encoder.encode(password);
        User user = new User(username, hashPwd, email, AuthType.KAKAO);

        return userRepository.save(user);
    }

    public void update(User requestUser) {

        if (requestUser.getOauth() != AuthType.NORMAL) {
            throw new IllegalArgumentException("소셜 계정은 회원정보 변경이 불가합니다.");
        } else {
            User findUser = userRepository.findById(requestUser.getId())
                    .orElseThrow(() -> {
                        return new IllegalArgumentException("회원 수정 실패 : 존재하지 않는 회원입니다.");
                    });

            findUser.setPassword(encoder.encode(requestUser.getPassword()));
            findUser.setEmail(requestUser.getEmail());
        }
    }

    @Transactional(readOnly = true)
    public User find(String username) {

        return userRepository.findByUsername(username).orElse(null);

    }
}
