package com.project.hotel_management_system.user.account.repository;

import org.springframework.data.repository.CrudRepository;

public interface UserAccountJpaDao extends CrudRepository<UserAccountEntity, Integer>, UserAccountCustomDao {
    String getUserId(String nickname, String password);
}
