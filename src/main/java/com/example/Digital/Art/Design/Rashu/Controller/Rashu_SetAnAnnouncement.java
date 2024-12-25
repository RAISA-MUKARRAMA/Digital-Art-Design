package com.example.Digital.Art.Design.Rashu.Controller;

import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_Admin;
import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_Announcement;
import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_AnnouncementReq;
import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_Counter;
import com.example.Digital.Art.Design.Rashu.Repository.Rashu_CounterRepository;
import com.example.Digital.Art.Design.Rashu.Repository.Rashu_AnnouncementRepository;
import com.example.Digital.Art.Design.Rashu.Repository.Rashu_AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class Rashu_SetAnAnnouncement {
    @Autowired
    private Rashu_AdminRepository adminRepository;
    @Autowired
    private Rashu_CounterRepository counterRepository;
    @Autowired
    private Rashu_AnnouncementRepository announcementRepository;

    @Transactional
    @PostMapping("/Rashu_SetAnnouncement")
    public ResponseEntity<?> setAnnouncementWithPassword(
            @RequestBody Rashu_AnnouncementReq requestPayload
    ) {
        try {
            // Use the 'id' from the URL path
            if (requestPayload.id() == null ) {
                // Return an error response as JSON
                return ResponseEntity.badRequest().body(
                        "{\"status\":\"error\",\"message\":\"All form fields are required.\"}"
                );
            }

            Rashu_Admin admin = adminRepository.findById(requestPayload.id()).orElse(null);
            if (admin == null || !admin.getPass().equals(requestPayload.getConfirmPassword())) {
                return ResponseEntity.badRequest()
                        .body("{\"status\":\"error\",\"message\":\"Wrong password\"}");
            }

            Rashu_Counter counter = counterRepository.findById(0L).orElseGet(Rashu_Counter::new);
            long newId = counter.getAnnc_Code() + 1;
            counter.setAnnc_Code(newId);
            Rashu_Announcement announcement = new Rashu_Announcement();

            announcement.setAdmin(admin);
            announcement.setAnc_code(newId);
            announcement.setAnc_Title(requestPayload.getAnc_Title());
            announcement.setDate(LocalDateTime.now());
            announcement.setDescription(requestPayload.getDescription());

            announcementRepository.save(announcement);
            counterRepository.save(counter);

            return ResponseEntity.ok().body("Announcement set successfully.");
        } catch (Exception e) {
            // Handle server errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"status\":\"error\",\"message\":\"Error: " + e.getMessage() + "\"}");
        }
    }
}
