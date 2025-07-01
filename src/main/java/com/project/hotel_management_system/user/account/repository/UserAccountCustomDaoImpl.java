package com.project.hotel_management_system.user.account.repository;

import com.project.hotel_management_system.authentication.controller.UserDto;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.hibernate.query.Query;

import java.util.List;

@Repository
public class UserAccountCustomDaoImpl implements UserAccountCustomDao {

    @Autowired
    private final EntityManager entityManager;

    public UserAccountCustomDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public String getUserId(String username, String password) {
         List<String> result = entityManager
                .createNativeQuery("select id from user_account where username=?1 and password=?2")
                .setParameter(1, username)
                .setParameter(2, password)
                .unwrap(Query.class)
                .setTupleTransformer((tuples, _) -> tuples[0])
                .getResultList();
         return result.isEmpty() ? null : result.getFirst();
    }

    @Override
    public UserDto getUserDtoByLogin(String username) {
        String query = """
                SELECT new com.project.hotel_management_system.authentication.controller.UserDto(u.id, u.username, u.password)
                FROM UserAccountEntity u
                WHERE u.username = :username
                """;
        return entityManager
                .createQuery(query, UserDto.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}
