package com.chany.blog.dto;

import lombok.*;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @Builder
public class ResponseDto<T> {
    int status;
    T data;
}
