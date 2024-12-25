package com.example.Digital.Art.Design.Raisa.controller;

import com.example.Digital.Art.Design.Raisa.model.AdminD;
import com.example.Digital.Art.Design.Raisa.model.AnnouncementD;
import com.example.Digital.Art.Design.Raisa.model.DesignerD;
import com.example.Digital.Art.Design.Raisa.model.MessageD;
import com.example.Digital.Art.Design.Raisa.repository.AdminRepo;
import com.example.Digital.Art.Design.Raisa.repository.AnnouncementRepo;
import com.example.Digital.Art.Design.Raisa.repository.DesignerRepo;
import com.example.Digital.Art.Design.Raisa.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class DesignerAnnouncementController {

    @Autowired
    private AnnouncementRepo announcementRepo;

    @Autowired
    private DesignerRepo designerRepo;


    @GetMapping("/DesignergetAnnouncement")
    public ResponseEntity<?> DesignergetAnnouncement(@RequestParam Long id, Model model) {
        if (id == null ) {
            // Handle missing parameters
            return ResponseEntity.badRequest().body("ID required");
        }
        System.out.println(id);
        DesignerD dsr = designerRepo.findById(id).orElse(null);
        if (dsr != null){
            List<AnnouncementD> announcementDS = announcementRepo.findAll();

            model.addAttribute("announcements", announcementDS);
            return ResponseEntity.ok(model);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
