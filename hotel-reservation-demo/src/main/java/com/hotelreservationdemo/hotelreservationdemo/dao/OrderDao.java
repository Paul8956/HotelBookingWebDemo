package com.hotelreservationdemo.hotelreservationdemo.dao;

import com.hotelreservationdemo.hotelreservationdemo.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderDao extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT * FROM orders WHERE id=:id", nativeQuery = true)
    Order getorderById(Integer id);

    @Query(value = "SELECT * FROM orders WHERE username=:username", nativeQuery = true)
    Page<Order> getorderByUsername(String username, Pageable pageable);

}
