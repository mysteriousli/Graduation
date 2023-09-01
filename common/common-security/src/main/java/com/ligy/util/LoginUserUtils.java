package com.ligy.util;

import com.ligy.pojo.User;

/**
 * @Author lgy
 */
public class LoginUserUtils {

    private static final ThreadLocal<User>  LOGIN_USER = new ThreadLocal<>();

    public static void remove() {
        LOGIN_USER.remove();
    }

    public static void setLoginUser(User loginUser) {
        LOGIN_USER.set(loginUser);
    }

    public static User getLoginUser() {
        return LOGIN_USER.get();
    }
}
