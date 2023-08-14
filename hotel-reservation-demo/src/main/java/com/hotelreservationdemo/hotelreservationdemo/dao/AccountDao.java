package com.hotelreservationdemo.hotelreservationdemo.dao;

import com.hotelreservationdemo.hotelreservationdemo.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountDao extends JpaRepository<Account, String> {
    @Query(value = "SELECT * FROM account WHERE username = :username AND userpwd = :password", nativeQuery = true)
    Account loginVerify(String username, String password);

    @Query(value = "SELECT * FROM account WHERE username = :username", nativeQuery = true)
    Account getAllByUsername(String username);
}
