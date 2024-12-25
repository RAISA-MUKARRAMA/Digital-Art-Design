package com.example.Digital.Art.Design.Raisa.controller;

import com.example.Digital.Art.Design.Raisa.model.*;
import com.example.Digital.Art.Design.Raisa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Transactional
@RestController
public class DesignerMyOrdersController {

    @Autowired
    private DesignerRepo designerRepo;

    @Autowired
    private DesignRepo designRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private OrderRepo orderRepo;

    @GetMapping("/designerGetMyOrders")
    public ResponseEntity<?> participate(@RequestParam Long id, Model model) {
        if (id == null) {
            // Handle missing parameters
            return ResponseEntity.badRequest().body("ID required");
        }
        System.out.println(id);
        DesignerD dsr = designerRepo.findById(id).orElse(null);
        if (dsr != null) {

            List<UserD> usr = userRepo.findAll();

            List<CategoryD> ctgr = categoryRepo.findAll();

            Set<DesignD> myDesigns = designRepo.findAllByDesigner_Id(id);

            List<OrderD> orders = orderRepo.findByDesignerId(id);

            model.addAttribute("orders", orders);
            model.addAttribute("users", usr);
            model.addAttribute("categories", ctgr);
            model.addAttribute("myDesigns", myDesigns);
            return ResponseEntity.ok(model);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/designerAcceptOrder")
    public ResponseEntity<?> designerAcceptOrder(@RequestParam Long id,
                                                 @RequestParam(value="order_code") Long order_code){
        if (id == null) {
            // Handle missing parameters
            return ResponseEntity.badRequest().body("ID required");
        }
        System.out.println(id);
        DesignerD dsr = designerRepo.findById(id).orElse(null);

        if (dsr != null) {
            OrderD orderD = orderRepo.getOne(order_code);
            orderD.setApproved(1);
            orderRepo.save(orderD);
            return ResponseEntity.ok("Order Accepted");
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/designerCompleteAnOrder")
    public ResponseEntity<?> designerParticipateInContest(@RequestParam Long id,
                                                          @RequestParam(value = "selectedDesign") Long desgn_code,
                                                          @RequestParam(value="order_code") Long order_code){
        if (id == null) {
            // Handle missing parameters
            return ResponseEntity.badRequest().body("ID required");
        }
        System.out.println(id);
        DesignerD dsr = designerRepo.findById(id).orElse(null);

        if (dsr != null) {

            DesignD designD = designRepo.getById(desgn_code);

            OrderD orderD = orderRepo.getOne(order_code);
            orderD.setApproved(2);
            orderD.setDesignD(designD);
            orderRepo.save(orderD);

            return ResponseEntity.ok("Order Completed Successfully");
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

}
