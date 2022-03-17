package com.chany.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.EnumType.STRING;


@NoArgsConstructor
@AllArgsConstructor
@Entity @Data @Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @Enumerated(STRING)
    private Role role = Role.USER;

    @Enumerated(STRING)
    private AuthType oauth = AuthType.NORMAL;

    @CreationTimestamp
    private Timestamp createDate;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password, String email, AuthType oauth) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.oauth = oauth;
    }
}
