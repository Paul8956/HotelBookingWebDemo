package com.hotelreservationdemo.hotelreservationdemo.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "account")
public class Account {
    @Id
    private String username;

    private String userpwd;
    @Column(updatable = false)
    private LocalDateTime createtime;

    private LocalDateTime latestupdate;

    public Account(){

    };

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createtime = now;
        latestupdate = now;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public LocalDateTime getLatestupdate() {
        return latestupdate;
    }

    public void setLatestupdate(LocalDateTime latestupdate) {
        this.latestupdate = latestupdate;
    }
}
