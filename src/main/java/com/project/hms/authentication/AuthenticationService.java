package com.project.hms.authentication;

import com.project.hms.user.account.repository.UserAccountJpaDao;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserAccountJpaDao userAccountJpaDao;

    public AuthenticationService(UserAccountJpaDao userAccountJpaDao) {
        this.userAccountJpaDao = userAccountJpaDao;
    }

//    public boolean authenticate(UserDto userDto, AuthenticationResponse response) {
//        String userId = userAccountJpaDao.getUserId(userDto.username());
//        if (Objects.nonNull(userId)) {
//            response.setUserId(userId);
//            response.setSuccess(Boolean.TRUE);
//            response.setMessage("Authentication data passed.");
//            return true;
//        } else {
//            response.setMessage("Invalid username or password.");
//            return false;
//        }
//    }
}
