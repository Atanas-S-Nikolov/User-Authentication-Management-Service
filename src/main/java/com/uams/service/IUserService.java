package com.uams.service;

import com.uams.model.internal.User;

public interface IUserService {

    User saveUser(User user);

    User updateUserLoginStatus(String username, String password, boolean isLoggedInStatus);

    User updateSearchHistory(String search, String username);

    void deleteUser(String username, String password);
}
