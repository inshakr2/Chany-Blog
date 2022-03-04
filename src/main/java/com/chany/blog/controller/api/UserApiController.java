package com.chany.blog.controller.api;

import com.chany.blog.dto.ResponseDto;
import com.chany.blog.model.User;
import com.chany.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserApiController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("CALL UserApiController.save");
        int result = userService.save(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
    }
}
