package com.project.hotel_management_system.user.account.repository;

import com.project.hotel_management_system.authentication.controller.UserDto;

public interface UserAccountCustomDao {
    String getUserId(String username, String password);

    UserDto getUserDtoByLogin(String login);
}
