package com.project.hms.modules.account.repository;

import com.project.hms.modules.account.entity.UserAccountEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountJpaDao extends CrudRepository<UserAccountEntity, Integer>, UserAccountCustomDao {
    String getUserId(String username, String password);
}
