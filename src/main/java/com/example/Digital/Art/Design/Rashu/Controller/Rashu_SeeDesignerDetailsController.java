package com.example.Digital.Art.Design.Rashu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.example.Digital.Art.Design.Rashu.EntityClasses.*;
import com.example.Digital.Art.Design.Rashu.Repository.*;
@Transactional
@RestController
@RequestMapping("/Rashu_designer/profile")
public class Rashu_SeeDesignerDetailsController {

    @Autowired
    private Rashu_DesignerRepository designerRepository;

    @GetMapping
    public ResponseEntity<?> getUserProfile(@RequestParam Long designer_id) {

        Rashu_Designer designer = designerRepository.findById(designer_id).orElse(null);

        if (designer != null) {
            return ResponseEntity.ok(designer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
