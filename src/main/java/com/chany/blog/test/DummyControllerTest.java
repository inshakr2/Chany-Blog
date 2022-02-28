package com.chany.blog.test;

import com.chany.blog.model.User;
import com.chany.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
@RequiredArgsConstructor
public class DummyControllerTest {

    private final UserRepository userRepository;

    @GetMapping("/dummy/user/{id}")
    public User user(@PathVariable int id) {
//        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//            @Override
//            public IllegalArgumentException get() {
//                return new IllegalArgumentException("존재하지 않는 유저입니다. id : " + id);
//            }
//        });

        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다. id : " + id));

        return user;
    }

    @GetMapping("/dummy/user/all")
    public List<User> users() {
        return userRepository.findAll();
    }

    @GetMapping("/dummy/user")
    public Page<User> pageUsers(
            @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC)Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @PostMapping("/dummy/user")
    public String join(User user) {
//        System.out.println("username = " + user.getUsername());
//        System.out.println("password = " + user.getPassword());
//        System.out.println("email = " + user.getEmail());

        userRepository.save(user);
        return "회원 가입 완료";
    }

    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id,
                           @RequestBody User requestUser) {
        System.out.println("requestUser.getPassword() = " + requestUser.getPassword());
        System.out.println("requestUser.getEmail() = " + requestUser.getEmail());

        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다. id : " + id));

        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());
        return user;
    }

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id) {
        userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다. id : " + id));

        userRepository.deleteById(id);

        return "회원이 삭제되었습니다. id :" +id ;
    }
}
