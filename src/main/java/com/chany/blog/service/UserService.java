package com.chany.blog.service;

import com.chany.blog.model.User;
import com.chany.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
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

}
