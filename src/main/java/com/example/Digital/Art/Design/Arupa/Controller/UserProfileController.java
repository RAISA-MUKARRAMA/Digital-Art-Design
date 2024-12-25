package com.example.Digital.Art.Design.Arupa.Controller;

import com.example.Digital.Art.Design.Arupa.EntityClasses.UserContest;
import com.example.Digital.Art.Design.Arupa.EntityClasses.UserOrder;
import com.example.Digital.Art.Design.Arupa.EntityClasses.UserProfile;
import com.example.Digital.Art.Design.Arupa.Repository.UserContestRepository;
import com.example.Digital.Art.Design.Arupa.Repository.UserOrderRepository;
import com.example.Digital.Art.Design.Arupa.Repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserProfileController {
    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserOrderRepository uor;

    @Autowired
    UserContestRepository ucr;

    @GetMapping("/UserProfile/{id}")
    public String userProfile(@PathVariable(value = "id") Long id, Model model){
        UserProfile user = userProfileRepository.findById(id).orElse(null);
        List<UserOrder> orders = uor.findByuId(id);
        model.addAttribute("orders", orders.size());

        List<UserContest> contests = ucr.findByuserId(id);
        model.addAttribute("contests", contests.size()) ;

        model.addAttribute("user", user);
        return "/Arupa/userProfile.html";
    }
}
