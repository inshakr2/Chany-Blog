package com.chany.blog.service;

import com.chany.blog.dto.UserSaveRequestDto;
import com.chany.blog.dto.UserUpdateRequestDto;
import com.chany.blog.model.AuthType;
import com.chany.blog.model.User;
import com.chany.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.MethodNotAllowedException;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final AuthenticationManager manager;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public Integer save(UserSaveRequestDto dto) {

        User user = new User(
                dto.getUsername(),
                dto.getPassword(),
                dto.getEmail()
                );

        if (userRepository.findByUsername(user.getUsername()).isEmpty()) {

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

        } else {
            throw new IllegalArgumentException("회원 가입 실패 : 이미 존재하는 회원입니다.");
        }

    }

    public User saveFromKakao(String username, String email, String password) {

        String hashPwd = encoder.encode(password);
        User user = new User(username, hashPwd, email, AuthType.KAKAO);

        return userRepository.save(user);
    }

    public Integer update(UserUpdateRequestDto requestUserDto) {

        if (requestUserDto.getOauth() == AuthType.NORMAL) {

            try {
                User findUser = userRepository.findById(requestUserDto.getId())
                        .orElseThrow(() -> {
                            return new IllegalArgumentException("회원 수정 실패 : 존재하지 않는 회원입니다.");
                        });

                findUser.setPassword(encoder.encode(requestUserDto.getPassword()));
                findUser.setEmail(requestUserDto.getEmail());

                // 세션 등록

                Authentication authenticate = manager.authenticate(
                        new UsernamePasswordAuthenticationToken(requestUserDto.getUsername(), requestUserDto.getPassword())
                );
                SecurityContextHolder.getContext().setAuthentication(authenticate);

                return 1;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return -1;
        } else {
            throw new IllegalArgumentException("소셜 계정은 회원정보 변경이 불가합니다.");
        }

    }

    @Transactional(readOnly = true)
    public User find(String username) {

        return userRepository.findByUsername(username).orElse(null);

    }

    public void leave(int userId) {

        userRepository.deleteById(userId);
    }

    public int userCheck(String username) {

        if (userRepository.findByUsername(username).isEmpty()) {
            return 1;
        } else {
            return -1;
        }

    }
}
