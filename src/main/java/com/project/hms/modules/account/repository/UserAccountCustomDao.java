package com.project.hms.modules.account.repository;

import com.project.hms.modules.authentication.dto.UserPrincipal;

public interface UserAccountCustomDao {
    String getUserId(String username, String password);

    UserPrincipal getUserDtoByLogin(String login);
}
