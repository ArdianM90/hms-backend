package com.project.hms.user.account.repository;

import org.springframework.data.repository.CrudRepository;

public interface UserAccountJpaDao extends CrudRepository<UserAccountEntity, Integer>, UserAccountCustomDao {
    String getUserId(String username, String password);
}
