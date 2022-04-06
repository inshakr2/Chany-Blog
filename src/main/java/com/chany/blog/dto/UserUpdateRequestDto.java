package com.chany.blog.dto;

import com.chany.blog.model.AuthType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestDto {

    private int id;
    private String username;
    private String password;
    private String email;
    private AuthType oauth;
}
