package com.hotelreservationdemo.hotelreservationdemo.dao;

import com.hotelreservationdemo.hotelreservationdemo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User,String> {
    @Query(value = "SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.username = :username")
    boolean existsByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM user WHERE username = :username", nativeQuery = true)
    User getAllByname(String username);

}
