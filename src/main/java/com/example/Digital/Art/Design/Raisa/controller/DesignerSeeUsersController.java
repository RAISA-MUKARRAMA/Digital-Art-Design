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
public class DesignerSeeUsersController {

    @Autowired
    private DesignerRepo designerRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ContestRepo contestRepo;

    @Autowired
    private OrderRepo orderRepo;

    @GetMapping("/designerGetUserList")
    public ResponseEntity<?> participate(@RequestParam Long id, Model model) {
        if (id == null) {
            // Handle missing parameters
            return ResponseEntity.badRequest().body("ID required");
        }
        System.out.println(id);
        DesignerD dsr = designerRepo.findById(id).orElse(null);
        if (dsr != null) {

            List<UserD> users = userRepo.findAll();

//            Long cntno = cntstRepo.contestHolded(adm.getAdmin_Id());
            Map<Long,Long> contestRequested = new HashMap<>();
            for(UserD userD:users){
                Long cntno = contestRepo.contestRequested(userD.getUser_id());
                contestRequested.put(userD.getUser_id(),cntno);
            }

            Map<Long,Long> designOrdered = new HashMap<>();
            for(UserD userD:users){
                Long ordno = orderRepo.designOrdered(userD.getUser_id());
                designOrdered.put(userD.getUser_id(),ordno);
            }

            model.addAttribute("users", users);
            model.addAttribute("contestRequested", contestRequested);
            model.addAttribute("designOrdered", designOrdered);
            return ResponseEntity.ok(model);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
