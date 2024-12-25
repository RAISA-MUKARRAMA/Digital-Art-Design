package com.example.Digital.Art.Design.Rashu.Controller;

import com.example.Digital.Art.Design.Rashu.EntityClasses.*;
import com.example.Digital.Art.Design.Rashu.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import jakarta.servlet.http.HttpSession;

@Controller
public class Rashu_SignInController {

    @Autowired
    private Rashu_UserRepository userRepository;
    @Autowired
    private Rashu_DesignerRepository designerRepository;
    @Autowired
    private Rashu_AdminRepository adminRepository;

    @PostMapping("/Rashu_signin")
    public ResponseEntity<String> signIn(HttpSession session, @RequestParam Long id, @RequestParam String password) {
        if (id != null && password != null) {
            if (id < 1000000000L) {
                // Handle admins
                Rashu_Admin admin = adminRepository.findById(id).orElse(null);
                if (admin != null) {
                    if (admin.getPass().equals(password)) {
                        session.setAttribute("user", "admin");
                        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("{\"role\": \"admin\", \"id\": " + id + "}");
                    } else {
                        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("{\"error\": \"Wrong Password\"}");
                    }
                }
            } else if ( id >= 5000000000L) {
                // Handle regular users
                Rashu_User user = userRepository.findById(id).orElse(null);
                if (user != null) {
                    if (user.getPass().equals(password)) {
                        session.setAttribute("user", "user");
                        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("{\"role\": \"user\", \"id\": " + id + "}");
                    } else {
                        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("{\"error\": \"Wrong Password\"}");
                    }
                }
            } else {
                // Handle designers
                Rashu_Designer designer = designerRepository.findById(id).orElse(null);
                if (designer != null) {
                    if (designer.getPass().equals(password)) {
                        session.setAttribute("user", "designer");
                        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("{\"role\": \"designer\", \"id\": " + id + "}");
                    } else {
                        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("{\"error\": \"Wrong Password\"}");
                    }
                }
            }
        }

        // Handle authentication failure
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("{\"error\": \"Wrong User ID\"}");
    }

}
