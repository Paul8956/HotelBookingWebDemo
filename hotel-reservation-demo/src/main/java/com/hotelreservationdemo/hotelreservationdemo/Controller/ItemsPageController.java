package com.hotelreservationdemo.hotelreservationdemo.Controller;

import com.hotelreservationdemo.hotelreservationdemo.dao.ItemDao;
import com.hotelreservationdemo.hotelreservationdemo.dao.OrderDao;
import com.hotelreservationdemo.hotelreservationdemo.domain.Items;
import com.hotelreservationdemo.hotelreservationdemo.domain.Order;
import com.hotelreservationdemo.hotelreservationdemo.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Controller
public class ItemsPageController {

    @Resource
    private ItemDao itemDao;

    @Resource
    private OrderDao orderDao;

    @GetMapping("/home")
    public String getHomePage(HttpSession httpSession, @PageableDefault(size = 5, sort = {"itemID"}, direction = Sort.Direction.ASC) Pageable pageable,
                              Model model){
        Page<Items> page1 = itemDao.findAll(pageable);
        User user = (User)httpSession.getAttribute("userdata");
        model.addAttribute("cards", page1);
        model.addAttribute("userdata", user);
        return "home";
    }
    @GetMapping("/home/{country}")
    public String getHomePageByCountry(HttpSession httpSession, @PathVariable String country, @PageableDefault(size = 5, sort = {"itemID"}, direction = Sort.Direction.ASC) Pageable pageable,
                                       Model model){
        Page<Items> page1 = itemDao.findByCountry(country, pageable);
        User user = (User)httpSession.getAttribute("userdata");
        model.addAttribute("cards", page1);
        model.addAttribute("userdata", user);
        return "home";
    }

    @GetMapping("/home/itempage/{itemID}")
    public String getItemPage(@PathVariable Integer itemID, HttpSession httpSession, Model model){
        User user = (User)httpSession.getAttribute("userdata");
        Items item = itemDao.findByID(itemID);
        if(user != null){
            model.addAttribute("item", item);
            model.addAttribute("userdata" ,user);
        }else{
            model.addAttribute("item", item);
        }
        return "itempage";
    }


    @PostMapping("/home/itempage/order")
    public String order(HttpSession httpSession, Order order){

        LocalDateTime currentTime = LocalDateTime.now().withNano(0);
        order.setCreatetime(currentTime);
        order.setStatus(0);

        if(order.getSingleroom() == null){order.setSingleroom(0);}
        if(order.getDoubleroom() == null){order.setDoubleroom(0);}
        if(order.getQuadroom() == null){order.setQuadroom(0);}

        Long totalDays = totalDays(order.getCheckin_time(), order.getCheckout_time());
        System.out.println(totalDays);
        order.setTotalprice(totalCount(totalDays, order.getHotel_name(), order.getSingleroom(), order.getDoubleroom(), order.getQuadroom()));

        orderDao.save(order);
        return "redirect:/home";
    }

    public Integer totalCount(Long totalDays, String hotel_name, Integer singleroom, Integer doubleroom, Integer quadroom){

        Integer tDays = totalDays.intValue();
        Integer totalcount =
                ((itemDao.findSingleroomPrice(hotel_name) * singleroom) +
                        (itemDao.findDoubleroomPrice(hotel_name) * doubleroom) +
                        (itemDao.findQuadroomPrice(hotel_name) * quadroom)) * tDays;
        return totalcount;
    }

    public Long totalDays(LocalDateTime checkin_time, LocalDateTime checkout_time){

        LocalDate checkinDate = checkin_time.toLocalDate();
        LocalDate checkoutDate = checkout_time.toLocalDate();

        return ChronoUnit.DAYS.between(checkinDate, checkoutDate);
    }

}
