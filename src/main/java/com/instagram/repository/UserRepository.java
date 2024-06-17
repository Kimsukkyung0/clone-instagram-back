package com.instagram.repository;

import com.instagram.insta.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public Optional<User> findByEmail(String email);

    public Optional<User> findByUsername(String username);

    @Query("SELECT u From User u WHERE u.userId IN:users")
    public List<User> findAllUserByUserIds(@Param("users") List<Integer> userIdList);

    @Query("SELECT distinct u FROM User u WHERE u.username LIKE %:query% OR u.email LIKE %:query%")
    public List<User> FindByQuery(@Param("query") String query);

}
