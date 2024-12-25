package com.example.Digital.Art.Design.Raisa.controller;

import com.example.Digital.Art.Design.Raisa.model.CategoryD;
import com.example.Digital.Art.Design.Raisa.model.DesignD;
import com.example.Digital.Art.Design.Raisa.model.DesignerD;
import com.example.Digital.Art.Design.Raisa.repository.CategoryRepo;
import com.example.Digital.Art.Design.Raisa.repository.DesignRepo;
import com.example.Digital.Art.Design.Raisa.repository.DesignerRepo;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Transactional
@RestController
@RequestMapping("/designerAllDesigns")
public class DesignerAllDesignsController {

    @Autowired
    private DesignerRepo designerRepo;

    @Autowired
    private DesignRepo designRepo;

    @Autowired
    private CategoryRepo categoryRepo;


    @GetMapping
    public ResponseEntity<?> mydesign(@RequestParam Long id, Model m) {

        try {
            System.out.println(id);
            if (id == null ) {
                // Handle missing parameters
                return ResponseEntity.badRequest().body("Both adminId and password are required.");
            }

            DesignerD dsr = designerRepo.getOne(id);

            if(dsr!=null){
                List<DesignD> dsns = designRepo.findAll();
                List<DesignerD> dsnrs = designerRepo.findAll();
//
                Map<Integer, String> ctData = new HashMap<>();
                for(DesignD d:dsns){
                    Integer ctgrCode = d.getCtgr_Code();
                    CategoryD ctgr = categoryRepo.getOne(ctgrCode);
                    ctData.put(ctgrCode,ctgr.getCtgr_Title());
                }

                m.addAttribute("designers", dsnrs);
//                m.addAttribute("content", ppData);
                m.addAttribute("DSN", dsns);
                m.addAttribute("ctData",ctData);

                return ResponseEntity.ok(m);
            }
            // Handle any other exceptions here
            else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // Handle the exception and return an appropriate ResponseEntity
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request.");
        }
    }

    // Method to determine media type based on file content
    private Optional<MediaType> determineMediaType(String fileContent) {
        // Use MediaTypeFactory to determine media type based on file content
        Optional<MediaType> mediaType = MediaTypeFactory.getMediaType(fileContent);
        return mediaType;
    }
}
