package com.hotelreservationdemo.hotelreservationdemo.dao;

import com.hotelreservationdemo.hotelreservationdemo.domain.Items;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemDao extends JpaRepository<Items, Integer> {

    @Query(value = "SELECT *  FROM items WHERE country = :country", nativeQuery = true)
    Page<Items> findByCountry(String country, Pageable pageable);

    @Query(value = "SELECT * FROM items WHERE itemID = :itemID", nativeQuery = true)
    Items findByID(Integer itemID);

    @Query(value = "SELECT singleroom FROM items WHERE name = :hotel_name", nativeQuery = true)
    Integer findSingleroomPrice(String hotel_name);

    @Query(value = "SELECT doubleroom FROM items WHERE name = :hotel_name", nativeQuery = true)
    Integer findDoubleroomPrice(String hotel_name);

    @Query(value = "SELECT quadroom FROM items WHERE name = :hotel_name", nativeQuery = true)
    Integer findQuadroomPrice(String hotel_name);
}
