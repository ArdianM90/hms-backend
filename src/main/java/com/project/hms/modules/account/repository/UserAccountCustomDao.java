package com.project.hms.modules.account.repository;

import com.project.hms.modules.authentication.dto.UserPrincipal;

public interface UserAccountCustomDao {
    UserPrincipal getUserPrincipalByLogin(String login);
}
