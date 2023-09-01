package com.ligy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;

    private String userName;

    private String name;

    private String email;

    private Integer role;

    private String iphone;

    private String password;

    private String uums;

    private String place;
}