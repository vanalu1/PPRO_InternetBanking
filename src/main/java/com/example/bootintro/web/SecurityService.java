package com.example.bootintro.web;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
