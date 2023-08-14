package com.hotelreservationdemo.hotelreservationdemo.form;

import com.hotelreservationdemo.hotelreservationdemo.domain.Account;
import com.hotelreservationdemo.hotelreservationdemo.domain.User;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
public class Register {
    public static final String Phone_REG = "09\\d{8}";
    @NotBlank
    @Length(min = 8, message = "使用者名稱至少8位")
    @Id
    private String username;
    @NotBlank
    @Length(min = 8, message = "密碼至少8位")
    private String userpwd;
    @NotBlank
    private String confirmuserpwd;
    @NotBlank
    private String fname;
    @NotBlank
    private String lname;
    @Pattern(regexp = Phone_REG, message = "請輸入正確手機號")
    private String phone;
    @NotBlank
    @Email
    private String mail;

    public Register() {
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

    public String getConfirmuserpwd() {
        return confirmuserpwd;
    }

    public void setConfirmuserpwd(String confirmuserpwd) {
        this.confirmuserpwd = confirmuserpwd;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    public boolean confirmPWD(){
        if(this.userpwd.equals(this.confirmuserpwd)){
            return true;
        }else{return false;}
    }
    public Account covertToAccount(){
        Account account = new RegistertoAccount().convert(this);
        return account;
    }

    public User convertToUser(){
        User user = new RegistertoUser().convert(this);
        return  user;
    }


    private class RegistertoAccount implements RegisterConvert<Register, Account> {
        @Override
        public Account convert(Register register) {
            Account account = new Account();
            BeanUtils.copyProperties(register, account);
            return account;
        }
    }

    private class RegistertoUser implements RegisterConvert<Register, User> {

        @Override
        public User convert(Register register) {
            User user = new User();
            BeanUtils.copyProperties(register, user);
            return user;
        }
    }
}
