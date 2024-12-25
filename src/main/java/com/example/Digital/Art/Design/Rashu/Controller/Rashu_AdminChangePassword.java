package com.example.Digital.Art.Design.Rashu.Controller;


import com.example.Digital.Art.Design.Rashu.EntityClasses.*;
import com.example.Digital.Art.Design.Rashu.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
public class Rashu_AdminChangePassword {
    @Autowired
    private Rashu_AdminRepository adminRepository;
    @Transactional
    @PostMapping("/Rashu_changedpassword")
    public ResponseEntity<?> setAContest(@RequestBody Rashu_ChangePassword changepassword) {
        try {


            Rashu_Admin admin = adminRepository.findById(changepassword.getId()).orElse(null);
            if (admin == null || !admin.getPass().equals(changepassword.getOldPassword())) {
                return ResponseEntity.badRequest()
                        .body("{\"status\":\"error\",\"message\":\"Wrong password\"}");
            }

            // Your business logic here to process the contestSet object
            admin.setAdds(admin.getAdds());
            admin.setBio(admin.getBio());
            admin.setContact(admin.getContact());
            admin.setDob(admin.getDob());
            admin.setEmail(admin.getName());
            admin.setId(admin.getId());
            admin.setName(admin.getName());
            admin.setPass(changepassword.getConfirmNewPassword());

            adminRepository.save(admin);
            // Return a success response
            return ResponseEntity.ok().body("Password Changed successfully.");
        } catch (Exception e) {
            // Handle server errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"status\":\"error\",\"message\":\"Error: " + e.getMessage() + "\"}");
        }
    }
}
