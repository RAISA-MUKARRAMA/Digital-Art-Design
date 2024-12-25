package com.example.Digital.Art.Design.Rashu.Controller;

import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_Admin;
import com.example.Digital.Art.Design.Rashu.Repository.Rashu_AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Transactional
@RestController
@RequestMapping("/Rashu_admins")
public class Rashu_SeeAdminController {
    @Autowired
    private Rashu_AdminRepository adminRepository; // Assuming you have an AdminRepository

    @GetMapping("/list")
    public ResponseEntity<List<Rashu_Admin>> getAdminList() {
        List<Rashu_Admin> adminList = adminRepository.findAll(); // Fetch a list of admins from your repository
        return ResponseEntity.ok(adminList);
    }
}
