package com.chany.blog.service;

import com.chany.blog.model.User;
import com.chany.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void update(User requestUser) {
        User findUser = userRepository.findById(requestUser.getId())
                .orElseThrow(() -> {
                    return new IllegalArgumentException("회원 수정 실패 : 존재하지 않는 회원입니다.");
                });

        findUser.setPassword(encoder.encode(requestUser.getPassword()));
        findUser.setEmail(requestUser.getEmail());
    }
}
