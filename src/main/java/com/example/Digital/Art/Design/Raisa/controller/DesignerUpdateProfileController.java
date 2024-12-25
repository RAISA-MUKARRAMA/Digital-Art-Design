package com.example.Digital.Art.Design.Raisa.controller;

import com.example.Digital.Art.Design.Raisa.model.DesignerD;
import com.example.Digital.Art.Design.Raisa.repository.DesignerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

import static java.lang.Long.parseLong;

@Transactional
@RestController
@RequestMapping("/designerUpdateProfile")
public class DesignerUpdateProfileController {

    @Autowired
    private DesignerRepo designerRepo;

    @PostMapping
    public ResponseEntity<?> updated(@RequestParam Long id,
                                     @RequestParam(value = "Dname") String Dname,
                                     @RequestParam(value = "newpas") String newpas,
                                     @RequestParam(value = "email") String email,
                                     @RequestParam(value = "contact") String contact,
                                     @RequestParam(value = "address") String address,
                                     @RequestParam(value = "bio") String bio,
                                     @RequestParam(name = "dob", required = false) LocalDate dob,
                                     @RequestParam(value = "propic") MultipartFile propic,
                                     Model model)
    {
        if (id == null ) {
            // Handle missing parameters
            return ResponseEntity.badRequest().body("ID required");
        }
        System.out.println(id);
        DesignerD dsr = designerRepo.findById(id).orElse(null);
        if (dsr != null){
            System.out.println(Dname);
            if(!Dname.isEmpty()) {
                dsr.setDesignerName(Dname);
            }
            if(!newpas.isEmpty()){
                dsr.setPassword(newpas);
            }
            if(!email.isEmpty()){
                dsr.setEmailAddress(email);
            }
            if(!contact.isEmpty()){
                dsr.setContactNo(contact);
            }
            if(!address.isEmpty()){
                dsr.setAddress(address);
            }
            if(dob!=null){
                dsr.setDateOfBirth(dob);
            }
            if(!bio.isEmpty()){
                dsr.setBio(bio);
            }
            try {
                if (!propic.isEmpty()) {
                    byte[] bcont = propic.getBytes();
                    dsr.setProfilePhoto(bcont);
                    // Now you have the content of the file as a byte array (fileBytes)
                    // Your handling logic using the byte array
                }
            } catch (IOException e) {
                // Handle the IOException
                return ResponseEntity.badRequest().body("Failed to read the file.");
            }
            designerRepo.save(dsr);
            return ResponseEntity.ok("Profile updated Successfully");
        }
        else{
            return ResponseEntity.notFound().build();
        }

    }
}
