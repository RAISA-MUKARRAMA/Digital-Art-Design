package com.example.Digital.Art.Design.Rashu.Controller;

import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_User;
import com.example.Digital.Art.Design.Rashu.Repository.Rashu_UserRepository;
import org.hibernate.annotations.processing.Find;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.*;
@Transactional
@RestController
@RequestMapping("/Rashu_users")
public class Rashu_SeeUserController {
    @Autowired
    private Rashu_UserRepository userRepository; // Assuming you have a UserRepository

    @GetMapping("/list")
    public ResponseEntity<List<Rashu_User>> getUserList() {
        List<Rashu_User> userList = userRepository.findAll(); // Fetch a list of users from your repository
        return ResponseEntity.ok(userList);
    }


    @GetMapping("/one")
    public ResponseEntity<Rashu_User> findUserById(@RequestParam("userId") Long userId) {
        Optional<Rashu_User> userOptional = userRepository.findById(userId); // Find user by id
        if (userOptional.isPresent()) {
            Rashu_User user = userOptional.get();
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
