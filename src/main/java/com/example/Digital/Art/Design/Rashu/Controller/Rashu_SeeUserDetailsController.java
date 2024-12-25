package com.example.Digital.Art.Design.Rashu.Controller;

import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.example.Digital.Art.Design.Rashu.EntityClasses.*;
import com.example.Digital.Art.Design.Rashu.Repository.*;

import java.util.List;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@RestController
@RequestMapping("/Rashu_user/profile")
public class Rashu_SeeUserDetailsController {

    @Autowired
    private Rashu_UserRepository userRepository;

    @Autowired
    private Rashu_Order_Repository orderRepository;

    @GetMapping
    public ResponseEntity<?> getUserProfile(@RequestParam Long user_id) {

        Rashu_User user = userRepository.findById(user_id).orElse(null);

        if (user != null) {
            System.out.println("Running java controller");
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
