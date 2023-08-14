package com.hotelreservationdemo.hotelreservationdemo.Controller;

import com.hotelreservationdemo.hotelreservationdemo.dao.ItemDao;
import com.hotelreservationdemo.hotelreservationdemo.dao.OrderDao;
import com.hotelreservationdemo.hotelreservationdemo.domain.Items;
import com.hotelreservationdemo.hotelreservationdemo.domain.Order;
import com.hotelreservationdemo.hotelreservationdemo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ItemsController {

    @Autowired
    private ItemDao itemDao;
    @Autowired
    private OrderDao orderDao;

    //itemlist
    @GetMapping("/gitems")
    public List<Items> getItem(){
        return itemDao.findAll();
    }

    @GetMapping("/itemlist")
    public String itemlist(HttpSession httpSession, @PageableDefault(size = 10, sort = {"itemID"}, direction = Sort.Direction.ASC) Pageable pageable,
                           Model model){
        Page<Items> page1 = itemDao.findAll(pageable);
        User user = (User)httpSession.getAttribute("userdata");
        model.addAttribute("page", page1);
        model.addAttribute("userdata", user);
        return "backendsystem";
    }
    @GetMapping("/itemlist/insert")
    public String insertItem(HttpSession httpSession, Model model){
        User user = (User)httpSession.getAttribute("userdata");
        model.addAttribute("items", new Items());
        model.addAttribute("userdata", user);
        return "insertitems";
    }
    @PostMapping("/itemlist/items")
    public String post(HttpSession httpSession, Items items, final RedirectAttributes attributes){
        Items items1 = itemDao.save(items);
        User user = (User)httpSession.getAttribute("userdata");
        if(items1 != null){
            attributes.addFlashAttribute("message", "<<" + items.getName() + ">> 商品更新成功!");
        }
        attributes.addFlashAttribute("userdata", user);
        return "redirect:/itemlist";
    }
    @GetMapping("/itemlist/{id}/delete")
    public String delete(@PathVariable Integer id, final RedirectAttributes attributes){
        itemDao.deleteById(id);
        attributes.addFlashAttribute("message", "商品刪除成功!");
        return "redirect:/itemlist";
    }

    @GetMapping("/itemlist/{id}/insert")
    public String updatelist(HttpSession httpSession, @PathVariable Integer id, Model model){
        User user = (User)httpSession.getAttribute("userdata");
        Optional<Items> items = itemDao.findById(id);
        model.addAttribute("items", items);
        model.addAttribute("userdata", user);
        return "insertitems";
    }


    //oderlist

    @GetMapping("/orderlist")
    public String orderlist(HttpSession httpSession, @PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
                           Model model){

        User user = (User)httpSession.getAttribute("userdata");
        Page<Order> page1 = orderDao.findAll(pageable);

        model.addAttribute("page", page1);
        model.addAttribute("userdata", user);
        return "orderlist";
    }

    @GetMapping("/orderlist/insert")
    public String insertOrder(HttpSession httpSession, Model model){
        User user = (User)httpSession.getAttribute("userdata");
        model.addAttribute("items", new Order());
        model.addAttribute("userdata", user);
        return "insertorder";
    }

    @PostMapping("/orderlist/orders")
    public String postOrder(HttpSession httpSession, @ModelAttribute Order order, final RedirectAttributes attributes){
        Order updateorder = orderDao.getorderById(order.getId());

        updateorder.setCheckin_time(order.getCheckin_time());
        updateorder.setCheckout_time(order.getCheckout_time());
        updateorder.setSingleroom(order.getSingleroom());
        updateorder.setDoubleroom(order.getDoubleroom());
        updateorder.setTotalprice(order.getTotalprice());
        updateorder.setStatus(order.getStatus());

        Order order1 = orderDao.save(updateorder);
        User user = (User)httpSession.getAttribute("userdata");
        if(order1 != null){
            attributes.addFlashAttribute("message", "訂單編號 [" + order.getId() + "] " + "更新成功!");
        }
        attributes.addFlashAttribute("userdata", user);
        return "redirect:/orderlist";
    }

    @GetMapping("/orderlist/{id}/insert")
    public String updateorderlist(HttpSession httpSession, @PathVariable Integer id, Model model){
        User user = (User)httpSession.getAttribute("userdata");
        Optional<Order> order = orderDao.findById(id);
        model.addAttribute("orders", order);
        model.addAttribute("userdata", user);
        return "insertorder";
    }

    @GetMapping("/orderlist/{orderid}")
    public String searchByorderIDtest(HttpSession httpSession, @PathVariable Integer orderid, Model model) {
        Order order = orderDao.getorderById(orderid);
        User user = (User) httpSession.getAttribute("userdata");
        model.addAttribute("userdata", user);

        Pageable pageable = PageRequest.of(0, 1, Sort.by("id").ascending());
        List<Order> orders = new ArrayList<>();
        if (order != null) {
            orders.add(order);
        }
        Page<Order> dummyPage = new PageImpl<>(orders, pageable, orders.size());

        model.addAttribute("page", dummyPage);

        return "orderlist";
    }



}
