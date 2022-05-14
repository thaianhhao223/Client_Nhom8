package com.iuh.clientnhom8.utils;

import com.iuh.clientnhom8.response.LoginInfoResponse;

import javax.servlet.http.HttpServletRequest;

public class UserUtils {

    public static LoginInfoResponse getUserInfo(HttpServletRequest request){
        return (LoginInfoResponse) request.getSession().getAttribute("userInfo");
    }

    public static void logout(HttpServletRequest request){
        request.getSession().removeAttribute("userInfo");
    }

    public static void login(HttpServletRequest request, LoginInfoResponse loginInfoResponse){
        request.getSession().setAttribute("userInfo", loginInfoResponse);
    }
}
