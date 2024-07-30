package com.optd.repository;

import com.optd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    User findByUserId(Integer userId);

    @Query("select u.username from User u where u.username=:username")
    String retrieveUsernameByUsername(@Param("username") String username);

    @Query("select u from User u")
    List<User> retrieveUserList();

}
