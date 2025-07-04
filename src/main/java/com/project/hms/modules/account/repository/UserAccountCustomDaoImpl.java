package com.project.hms.modules.account.repository;

import com.project.hms.modules.authentication.dto.UserPrincipal;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserAccountCustomDaoImpl implements UserAccountCustomDao {

    @Autowired
    private final EntityManager entityManager;

    public UserAccountCustomDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public UserPrincipal getUserPrincipalByLogin(String username) {
        String query = """
                SELECT new com.project.hms.modules.authentication.dto.UserPrincipal(u.id, u.username, u.password)
                FROM UserAccountEntity u
                WHERE u.username = :username
                """;
        return entityManager
                .createQuery(query, UserPrincipal.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}
