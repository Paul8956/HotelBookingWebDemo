package com.hotelreservationdemo.hotelreservationdemo.Controller;

import com.hotelreservationdemo.hotelreservationdemo.dao.ItemDao;
import com.hotelreservationdemo.hotelreservationdemo.dao.TestDao;
import com.hotelreservationdemo.hotelreservationdemo.domain.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class TestController {
    @Resource
    private TestDao testDao;
    @Resource
    private ItemDao itemDao;
    @GetMapping("/cards")
    public String showCards(Model model) {
        List<Test> cardList = testDao.findAll();
        model.addAttribute("cards", cardList);
        return "cards";
    }
}
