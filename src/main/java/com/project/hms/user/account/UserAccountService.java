package com.project.hms.user.account;

import com.project.hms.authentication.controller.UserDto;
import com.project.hms.user.account.repository.UserAccountJpaDao;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService implements UserDetailsService {

    private final UserAccountJpaDao userAccountJpaDao;

    public UserAccountService(UserAccountJpaDao userAccountJpaDao) {
        this.userAccountJpaDao = userAccountJpaDao;
    }

    @Override
    public UserDto loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAccountJpaDao.getUserDtoByLogin(username);
    }
}
