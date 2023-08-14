package com.hotelreservationdemo.hotelreservationdemo.Controller;

import com.hotelreservationdemo.hotelreservationdemo.dao.AccountDao;
import com.hotelreservationdemo.hotelreservationdemo.dao.OrderDao;
import com.hotelreservationdemo.hotelreservationdemo.dao.UserDao;
import com.hotelreservationdemo.hotelreservationdemo.domain.Account;
import com.hotelreservationdemo.hotelreservationdemo.domain.Order;
import com.hotelreservationdemo.hotelreservationdemo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class UserDataController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private AccountDao accountDao;

    @Autowired
    private OrderDao orderDao;


    //userdata
    @GetMapping("/user/updateUserData")
    public String updateForm(HttpSession httpSession, Model model){
        User user = (User)httpSession.getAttribute("userdata");
        model.addAttribute("userdata", user);
        return "updateuserdata";
    }

    @PostMapping("/user/updateUserData")
    public String updateUserData(HttpSession httpSession, User user, RedirectAttributes redirectAttributes){
        userDao.save(user);
        User user1 = userDao.getAllByname(user.getUsername());
        httpSession.setAttribute("userdata", user1);
        redirectAttributes.addFlashAttribute("userdata", user1);
        return "redirect:/home";
    }

    //userpassword

    @GetMapping("/user/changePSW")
    public String changePSW(HttpSession httpSession, Model model){
        User user = (User)httpSession.getAttribute("userdata");
        model.addAttribute("userdata", user);
        return "/updatepsw";
    }

    @PostMapping("/user/changePSW")
    public String updateUserPSW(HttpSession httpSession,
                                @RequestParam String username,
                                @RequestParam String currentpsw,
                                @RequestParam String newpsw,
                                @RequestParam String confirmpsw,
                                RedirectAttributes redirectAttributes){
        User user = (User)httpSession.getAttribute("userdata");
        redirectAttributes.addFlashAttribute("userdata", user);
        Account account = accountDao.getAllByUsername(username);
        if(!newpsw.equals(confirmpsw)){
            redirectAttributes.addFlashAttribute("changeFailed", 0);
            return "redirect:/user/changePSW";
        }else if(!currentpsw.equals(account.getUserpwd())){
            redirectAttributes.addFlashAttribute("changeFailed", 1);
            return "redirect:/user/changePSW";
        }else if(currentpsw.equals(newpsw)){
            redirectAttributes.addFlashAttribute("changeFailed", 2);
            return "redirect:/user/changePSW";

        }else if(newpsw.length() < 8){
            redirectAttributes.addFlashAttribute("changeFailed", 3);
            return "redirect:/user/changePSW";
        }else{
            account.setUserpwd(newpsw);
            accountDao.save(account);
            redirectAttributes.addFlashAttribute("changeSuccess", true);
            return "redirect:/login";
        }

    }

    //userorder
    @GetMapping("/user/getOrder/{username}")
    public String getOrder(HttpSession httpSession, @PathVariable String username,
                           @PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
                           Model model){
        User user = (User)httpSession.getAttribute("userdata");
        Page<Order> page1 = orderDao.getorderByUsername(username, pageable);

        model.addAttribute("userdata", user);
        model.addAttribute("page", page1);
        return "userorder";
    }
}
