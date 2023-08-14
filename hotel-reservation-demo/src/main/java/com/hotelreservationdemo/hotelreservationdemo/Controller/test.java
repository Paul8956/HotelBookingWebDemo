package com.hotelreservationdemo.hotelreservationdemo.Controller;

import com.hotelreservationdemo.hotelreservationdemo.dao.OrderDao;
import com.hotelreservationdemo.hotelreservationdemo.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {

    @Autowired
    OrderDao orderDao;


}
