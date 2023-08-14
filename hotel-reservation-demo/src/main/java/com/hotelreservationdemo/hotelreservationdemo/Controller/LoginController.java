package com.hotelreservationdemo.hotelreservationdemo.Controller;

import com.hotelreservationdemo.hotelreservationdemo.dao.AccountDao;
import com.hotelreservationdemo.hotelreservationdemo.dao.ItemDao;
import com.hotelreservationdemo.hotelreservationdemo.dao.UserDao;
import com.hotelreservationdemo.hotelreservationdemo.domain.Account;
import com.hotelreservationdemo.hotelreservationdemo.domain.Items;
import com.hotelreservationdemo.hotelreservationdemo.domain.User;
import com.hotelreservationdemo.hotelreservationdemo.form.Register;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {

    @Resource
    private UserDao userDao;
    @Resource
    private AccountDao accountDao;

    @Resource
    private ItemDao itemDao;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("register", new Register());
        return "register";
    }
    @PostMapping("/register")
    public String registerPost(@Valid Register register, BindingResult result, Model model){
        if(!register.confirmPWD()){
            result.rejectValue("confirmuserpwd", "confirmError","兩密碼不一致" );
        }
        if (userDao.existsByUsername(register.getUsername())) {
            result.rejectValue("username", "usernameError", "該用戶名已存在");
            return "register";
        }
        if(result.hasErrors()){
            return "register";
        }

        Account account = register.covertToAccount();
        User user = register.convertToUser();
        accountDao.save(account);
        userDao.save(user);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String loginVerify(@RequestParam String username, @RequestParam String password, @PageableDefault(size = 5, sort = {"itemID"}, direction = Sort.Direction.ASC) Pageable pageable,
                              RedirectAttributes redirectAttributes, HttpSession httpSession){
        Account verify = accountDao.loginVerify(username, password);
        if(verify != null){
            Page<Items> page1 = itemDao.findAll(pageable);
            User user = userDao.getAllByname(username);
            httpSession.setAttribute("userdata", user);
            redirectAttributes.addFlashAttribute("cards", page1);
            redirectAttributes.addFlashAttribute("userdata", user);
            return "redirect:/home";
        }else{
            redirectAttributes.addFlashAttribute("loginFailed", true);
            return "redirect:/login";
        }
    }

    //登出
    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/home";
    }


}
