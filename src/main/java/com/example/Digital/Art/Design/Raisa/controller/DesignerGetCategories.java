package com.example.Digital.Art.Design.Raisa.controller;


import com.example.Digital.Art.Design.Raisa.model.CategoryD;
import com.example.Digital.Art.Design.Raisa.model.DesignerD;
import com.example.Digital.Art.Design.Raisa.repository.CategoryRepo;
import com.example.Digital.Art.Design.Raisa.repository.DesignerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional
@RestController
@RequestMapping("/getCategory")
public class DesignerGetCategories {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private DesignerRepo designerRepo;

    @GetMapping
    public ResponseEntity<?> uploadDesign(@RequestParam Long id)
    {
        System.out.println("category " + id);
        if (id == null) {
            // Handle missing parameters
            return ResponseEntity.badRequest().body("ID required");
        }
        System.out.println(id);
        DesignerD dsr = designerRepo.findById(id).orElse(null);

        if (dsr != null) {
            List<CategoryD> ctr = categoryRepo.findAll();
            System.out.println(ctr);
            return ResponseEntity.ok(ctr);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}

//    @GetMapping("/uploaddesign/{designerId}")
//    public String uploadDesign(@PathVariable("designerId") long designerId, Model m, Model m1)
//    {
//        System.out.println("category " + designerId);
//        List<CategoryD> ctr = ctgrRepo.findAll();
//        DesignerD dsr = dsrRepo.getOne(designerId);
//        System.out.println(ctr);
//        m.addAttribute("categories",ctr);
//        m1.addAttribute("designer",dsr);
//        return "/Raisa/upload-design.html";
//    }