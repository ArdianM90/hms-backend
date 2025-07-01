package com.project.hms.user.account.repository;

import com.project.hms.authentication.controller.UserDto;

public interface UserAccountCustomDao {
    String getUserId(String username, String password);

    UserDto getUserDtoByLogin(String login);
}
