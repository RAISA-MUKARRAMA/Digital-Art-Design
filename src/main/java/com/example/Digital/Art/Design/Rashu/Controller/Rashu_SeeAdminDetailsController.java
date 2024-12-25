package com.example.Digital.Art.Design.Rashu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.example.Digital.Art.Design.Rashu.EntityClasses.*;
import com.example.Digital.Art.Design.Rashu.Repository.*;
@Transactional
@RestController
@RequestMapping("/Rashu_other/profile")
public class Rashu_SeeAdminDetailsController {

    @Autowired
    private Rashu_AdminRepository adminRepository;

    @GetMapping
    public ResponseEntity<?> getAllAdminProfile(@RequestParam Long admin_id) {

        Rashu_Admin admin = adminRepository.findById(admin_id).orElse(null);

        if (admin != null) {
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
