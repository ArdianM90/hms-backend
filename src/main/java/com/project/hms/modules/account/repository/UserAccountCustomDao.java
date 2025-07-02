package com.project.hms.modules.account.repository;

import com.project.hms.modules.authentication.dto.UserDto;

public interface UserAccountCustomDao {
    String getUserId(String username, String password);

    UserDto getUserDtoByLogin(String login);
}
