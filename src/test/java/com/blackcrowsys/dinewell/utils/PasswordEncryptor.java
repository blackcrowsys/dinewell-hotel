package com.blackcrowsys.dinewell.utils;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncryptor {

    public static void main(String[] args){
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String password = "test1234";
        System.out.println(password + ":[" + passwordEncoder.encode(password) + "]");
    }
}
