package com.chany.blog.controller.api;

import com.chany.blog.dto.ResponseDto;
import com.chany.blog.dto.UserSaveRequestDto;
import com.chany.blog.dto.UserUpdateRequestDto;
import com.chany.blog.model.User;
import com.chany.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        int result = userService.save(userSaveRequestDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
    }

    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody UserUpdateRequestDto userUpdateRequestDto) {

        int result = userService.update(userUpdateRequestDto);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
    }

    @DeleteMapping("/user")
    public ResponseDto<Integer> leave(@RequestParam int userId, HttpSession session) {
        System.out.println(userId);

        userService.leave(userId);
        session.invalidate();

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PostMapping("/auth/user/check")
    public ResponseDto<Integer> userCheck(@RequestParam String username) {
        int res = userService.userCheck(username);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), res);
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
