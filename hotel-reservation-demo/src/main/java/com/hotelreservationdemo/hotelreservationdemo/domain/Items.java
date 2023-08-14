package com.hotelreservationdemo.hotelreservationdemo.domain;

import javax.persistence.*;


@Entity
@Table(name = "items")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemID;
    private String name;
    private String country;
    private String address;
    private Integer status;
    private String tel;
    private Integer singleroom;
    private Integer doubleroom;
    private Integer quadroom;
    private String description;

    public Items() {
    }

    public Integer getItemID() {
        return itemID;
    }

    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
