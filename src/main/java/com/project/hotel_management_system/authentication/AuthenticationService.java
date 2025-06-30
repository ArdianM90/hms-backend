package com.project.hotel_management_system.authentication;

import com.project.hotel_management_system.authentication.controller.AuthenticationResponse;
import com.project.hotel_management_system.user.account.repository.UserAccountJpaDao;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService {

    private final UserAccountJpaDao userAccountJpaDao;

    public AuthenticationService(UserAccountJpaDao userAccountJpaDao) {
        this.userAccountJpaDao = userAccountJpaDao;
    }

    public boolean validateUserLoginData(final String nickname, final String password, AuthenticationResponse response) {
        String userId = userAccountJpaDao.getUserId(nickname, password);
        if (Objects.nonNull(userId)) {
            response.setUserId(userId);
            response.setSuccess(Boolean.TRUE);
            response.setMessage("Authentication data passed.");
            return true;
        } else {
            response.setMessage("Invalid username or password.");
            return false;
        }
    }
}
