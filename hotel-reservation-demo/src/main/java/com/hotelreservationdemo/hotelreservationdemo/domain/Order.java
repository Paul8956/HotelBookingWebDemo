package com.hotelreservationdemo.hotelreservationdemo.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String hotel_name;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime checkin_time;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime checkout_time;

    private Integer singleroom;

    private Integer doubleroom;

    private Integer quadroom;

    private Integer totalprice;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createtime;

    private Integer status;

    public Order(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public LocalDateTime getCheckin_time() {
        return checkin_time;
    }

    public void setCheckin_time(LocalDateTime checkin_time) {
        this.checkin_time = checkin_time;
    }

    public LocalDateTime getCheckout_time() {
        return checkout_time;
    }

    public void setCheckout_time(LocalDateTime checkout_time) {
        this.checkout_time = checkout_time;
    }

    public Integer getSingleroom() {
        return singleroom;
    }

    public void setSingleroom(Integer singleroom) {
        this.singleroom = singleroom;
    }

    public Integer getDoubleroom() {
        return doubleroom;
    }

    public void setDoubleroom(Integer doubleroom) {
        this.doubleroom = doubleroom;
    }

    public Integer getQuadroom() {
        return quadroom;
    }

    public void setQuadroom(Integer quadroom) {
        this.quadroom = quadroom;
    }

    public Integer getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Integer totalprice) {
        this.totalprice = totalprice;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
