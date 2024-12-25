package com.example.Digital.Art.Design.Arupa.Controller;

import com.example.Digital.Art.Design.Arupa.EntityClasses.*;
import com.example.Digital.Art.Design.Arupa.EntityClasses.UserDesigner;
import com.example.Digital.Art.Design.Arupa.Repository.*;
import com.example.Digital.Art.Design.Arupa.Repository.UDesignerRepository;
import com.example.Digital.Art.Design.Arupa.Repository.UserOrderRepository;
import jakarta.servlet.ServletOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.*;

@Controller
public class UserOrderController {
    @Autowired
    private CounterRepository counterRepository;
    @Autowired
    UCategoryRepository ucr;
    @Autowired
    UDesignerRepository udr;
    @Autowired
    UserOrderRepository uor;
    @Autowired
    AdminRepository ar;
    @Autowired
    UserRequestRepository urr;
    @Autowired
    UserDesignRepository userDesignRepository;
    @Autowired
    UserProfileRepository userProfileRepository;

    @GetMapping("/placeOrder/{userId}")
    public String placeOrder(@PathVariable(value = "userId") Long userId, Model model) {
        UserProfile user = userProfileRepository.findById(userId).orElse(null);
        model.addAttribute("user", user);
        return "/Arupa/user_start_project.html";
    }

    @PostMapping("/searchDesigner/{userId}")
    public String searchDesigner(@PathVariable(value = "userId") Long uId, @RequestParam String category,
                                 @RequestParam String title,
                                 @RequestParam String description, Model model) {
        UserCategory uCategory = ucr.findBycName(category);
        Set<UserDesigner> uDesigner = uCategory.getDesigners();

        for (UserDesigner designer : uDesigner) {
            UserDesigner d = udr.findById(designer.getId()).orElse(null);
            designer.setName(d.getName());
            designer.setEmail(d.getEmail());
            designer.setContact(d.getContact());
        }
        UserProfile user = userProfileRepository.findById(uId).orElse(null);
        model.addAttribute("designers", uDesigner);
        model.addAttribute("user", user);
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        return "/Arupa/category_designers.html";
    }

    @PostMapping("/chosenDesigner/{userId}/{title}/{description}")
    public String uOrder(@PathVariable(value = "userId") Long userId,
                         @PathVariable(value = "title") String title,
                         @PathVariable(value = "description") String description,
                         @RequestParam Long dId, Model model) {

        UserCounter counter = counterRepository.findById(0L).orElseGet(UserCounter::new);
        Long newId = counter.getOrder_Code() + 1;
        counter.setOrder_Code(newId);
        counterRepository.save(counter);
        UserOrder order = new UserOrder();
        order.setOrder_id(newId);
        order.setU_id(userId);
        order.setTitle(title);
        order.setDescription(description);
        order.setOrder_date(LocalDateTime.now());
        int approved = 0;
        order.setApproved(approved);
        order.setD_id(dId);
        uor.save(order);
        UserProfile user = userProfileRepository.findById(userId).orElse(null);
        model.addAttribute("user", user);
        return "redirect:/UserProfile/{userId}";
    }

    @GetMapping("/pendingOrders/{userId}")
    public String pending(@PathVariable(value = "userId") Long userId, Model model) {
        List<UserOrder> allOrders = uor.findByuId(userId);
        List<UserOrder> orders = new ArrayList<>();
        List<UserDesigner> designers = new ArrayList<>();
        UserProfile user = userProfileRepository.findById(userId).orElse(null);
        model.addAttribute("user", user);
        for (UserOrder u : allOrders) {
            if (u.getApproved() == 0) {
                orders.add(u);
                Long designerId = u.getD_id();
                UserDesigner designer = udr.findById(designerId).orElse(null);
                designers.add(designer);
            }
        }
        if (!orders.isEmpty()) {
            model.addAttribute("orders", orders);
            model.addAttribute("designers", designers);
        } else {
            model.addAttribute("message", "No pending orders to show.");
        }
        return "/Arupa/userInAndPendingOrderDetails.html";
    }

    @GetMapping("/inProgress/{userId}")
    public String inProgress(@PathVariable(value = "userId") Long userId, Model model) {
        List<UserOrder> allOrders = uor.findByuId(userId);
        List<UserOrder> orders = new ArrayList<>();
        List<UserDesigner> designers = new ArrayList<>();
        UserProfile user = userProfileRepository.findById(userId).orElse(null);
        model.addAttribute("user", user);
        for (UserOrder u : allOrders) {
            if (u.getApproved() == 1) {
                orders.add(u);
                Long designerId = u.getD_id();
                UserDesigner designer = udr.findById(designerId).orElse(null);
                designers.add(designer);
            }
        }
        if (!orders.isEmpty()) {
            model.addAttribute("orders", orders);
            model.addAttribute("designers", designers);
        } else {
            model.addAttribute("message", "No orders are in progress.");
        }
        return "/Arupa/userInAndPendingOrderDetails.html";
    }

    @GetMapping("/contest/{userId}")
    public String getAllAdmins(@PathVariable(value = "userId") Long userId, Model model) {
        List<UserAdmin> admins = ar.findAll();
        model.addAttribute("admins", admins);
        UserProfile user = userProfileRepository.findById(userId).orElse(null);
        model.addAttribute("user", user);
        return "/Arupa/user_admin_list.html";
    }

    @PostMapping("/selectedAdmin/{userId}")
    public String chosenAdmin(@PathVariable(value = "userId") Long userId,
                              @RequestParam Long adminId, Model model) {
        UserProfile user = userProfileRepository.findById(userId).orElse(null);
        model.addAttribute("adminId", adminId);
        model.addAttribute("user", user);
        System.out.println(adminId);
        return "/Arupa/user_start_contest.html";
    }

    @PostMapping("/request/{adminId}/{userId}")
    public String contestDetails(@PathVariable(value = "adminId") Long adminId,
                                 @PathVariable(value = "userId") Long uId,
                                 @RequestParam String description, Model model) {
        System.out.println(adminId);
        System.out.println(11);
        UserRequest request = new UserRequest();
        request.setUserId(uId);
        request.setAdminId(adminId);
        request.setDate(LocalDateTime.now());
        request.setDescription(description);
        System.out.println(1);
        urr.save(request);
        System.out.println(2);
        model.addAttribute("request", request);
        UserProfile user = userProfileRepository.findById(uId).orElse(null);
        model.addAttribute("user", user);
        return "redirect:/UserProfile/{userId}";
    }

    @GetMapping("/requestdetails/{userId}")
    public String requestDetails(@PathVariable(value = "userId") Long userId, Model model) {
        List<UserRequest> requests = urr.findByuserId(userId);
        if(requests.size()==0){
            model.addAttribute("message", "No requests sent yet.");
        }
        else{
            model.addAttribute("requests", requests);
        }
        UserProfile user = userProfileRepository.findById(userId).orElse(null);
        model.addAttribute("user", user);
        return "/Arupa/user_requests.html";
    }
}