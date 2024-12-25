package com.example.Digital.Art.Design.Rashu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_Admin;
import com.example.Digital.Art.Design.Rashu.Repository.Rashu_AdminRepository;
@Transactional
@RestController
@RequestMapping("/Rashu_protected/admin/profile")
public class Rashu_AdminProfileController {

    @Autowired
    private Rashu_AdminRepository adminRepository;

    @GetMapping
    public ResponseEntity<?> getAdminProfile(@RequestParam Long id) {
        if (id == null ) {
            // Handle missing parameters
            return ResponseEntity.badRequest().body("Both adminId and password are required.");
        }

        Rashu_Admin admin = adminRepository.findById(id).orElse(null);

        if (admin != null) {
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
