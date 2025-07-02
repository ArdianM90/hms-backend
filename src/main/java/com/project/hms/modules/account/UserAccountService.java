package com.project.hms.modules.account;

import com.project.hms.modules.authentication.dto.UserDto;
import com.project.hms.modules.account.repository.UserAccountJpaDao;
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
