package com.hotelreservationdemo.hotelreservationdemo.dao;

import com.hotelreservationdemo.hotelreservationdemo.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestDao extends JpaRepository<Test, String> {
}
