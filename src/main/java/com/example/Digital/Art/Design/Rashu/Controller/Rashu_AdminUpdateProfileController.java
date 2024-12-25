package com.example.Digital.Art.Design.Rashu.Controller;

import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_Admin;
import com.example.Digital.Art.Design.Rashu.Repository.Rashu_AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Optional;
@Transactional
@RestController
@RequestMapping("/api/Rashu_profile/admin")
public class Rashu_AdminUpdateProfileController {

    @Autowired
    private Rashu_AdminRepository adminRepository;

    @GetMapping("/{id}")
    public Optional<Rashu_Admin> getAdminProfile(@PathVariable Long id) {
        return adminRepository.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAdminProfile(@PathVariable Long id, @RequestBody Rashu_Admin updatedAdmin) {
        Optional<Rashu_Admin> adminOptional = adminRepository.findById(id);

        if (adminOptional.isPresent()) {
            Rashu_Admin admin = adminOptional.get();

            // Check if the currentPassword matches the admin's actual password
            if (admin.getPass().equals(updatedAdmin.getPass())) {
                admin.setName(updatedAdmin.getName());
                admin.setEmail(updatedAdmin.getEmail());
                admin.setContact(updatedAdmin.getContact());
                admin.setPass(updatedAdmin.getPass());
                admin.setAdds(updatedAdmin.getAdds());
                admin.setBio(updatedAdmin.getBio());

                adminRepository.save(admin);

                return ResponseEntity.ok("Profile updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong Password");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin profile not found");
        }
    }

}

