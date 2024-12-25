package com.example.Digital.Art.Design.Raisa.controller;

import com.example.Digital.Art.Design.Raisa.model.*;
import com.example.Digital.Art.Design.Raisa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Transactional
@RestController
public class DesignerSeeAdminsController {

    @Autowired
    private DesignerRepo designerRepo;

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private ContestRepo contestRepo;

    @GetMapping("/designerGetAdminList")
    public ResponseEntity<?> participate(@RequestParam Long id, Model model) {
        if (id == null) {
            // Handle missing parameters
            return ResponseEntity.badRequest().body("ID required");
        }
        System.out.println(id);
        DesignerD dsr = designerRepo.findById(id).orElse(null);
        if (dsr != null) {

            List<AdminD> admins = adminRepo.findAll();

//            Long cntno = cntstRepo.contestHolded(adm.getAdmin_Id());
            Map<Long,Long> contestHolded = new HashMap<>();
            for(AdminD adminD:admins){
                Long cntno = contestRepo.contestHolded(adminD.getAdmin_Id());
                contestHolded.put(adminD.getAdmin_Id(),cntno);
            }

            model.addAttribute("admins", admins);
            model.addAttribute("contestHolded", contestHolded);
            return ResponseEntity.ok(model);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
