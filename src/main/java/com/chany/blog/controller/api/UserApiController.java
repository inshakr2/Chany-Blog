package com.chany.blog.controller.api;

import com.chany.blog.dto.ResponseDto;
import com.chany.blog.model.User;
import com.chany.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("CALL UserApiController.save");
        int result = userService.save(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
    }

//    @PostMapping("/user/login")
//    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
//        System.out.println("CALL UserApiController.login");
//        User auth = userService.login(user);
//
//        if (auth != null) {
//            session.setAttribute("auth", auth);
//        }
//
//        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//    }



}
