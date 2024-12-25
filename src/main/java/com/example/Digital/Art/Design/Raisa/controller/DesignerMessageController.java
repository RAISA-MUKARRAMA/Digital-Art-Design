package com.example.Digital.Art.Design.Raisa.controller;

import com.example.Digital.Art.Design.Raisa.model.AdminD;
import com.example.Digital.Art.Design.Raisa.model.DesignerD;
import com.example.Digital.Art.Design.Raisa.model.MessageD;
import com.example.Digital.Art.Design.Raisa.repository.AdminRepo;
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
public class DesignerMessageController {

    @Autowired
    private MessageRepo messageRepository;

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private DesignerRepo designerRepo;

    @GetMapping("/getMessagesByDesigner")
    public ResponseEntity<?> getMessagesByDesigner(@RequestParam Long id, Model model) {
        if (id == null ) {
            // Handle missing parameters
            return ResponseEntity.badRequest().body("ID required");
        }
        System.out.println(id);
        DesignerD dsr = designerRepo.findById(id).orElse(null);
        if (dsr != null){
            List<MessageD> messageList = messageRepository.findByDesigner(dsr);
            List<AdminD> admins = adminRepo.findAll();

            model.addAttribute("messages", messageList);
            model.addAttribute("admins", admins);
            return ResponseEntity.ok(model);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/sendmessage")
    public ResponseEntity<?> getMessagesByDesigner(@RequestParam Long id,
                                                   @RequestParam(value = "selectedadm") Long selectedadm,
                                                   @RequestParam(value = "des") String des)
    {
        if (id == null ) {
            // Handle missing parameters
            return ResponseEntity.badRequest().body("ID required");
        }
        System.out.println(id);
        DesignerD dsr = designerRepo.findById(id).orElse(null);
        if (dsr != null){
            AdminD adm = adminRepo.getOne(selectedadm);
            MessageD msg = new MessageD();
            msg.setAdmin(adm);
            msg.setDesigner(dsr);
            msg.setDescription(des);
            msg.setDate(LocalDateTime.now());
            messageRepository.save(msg);
            return ResponseEntity.ok("Message Sent Successfully");
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
