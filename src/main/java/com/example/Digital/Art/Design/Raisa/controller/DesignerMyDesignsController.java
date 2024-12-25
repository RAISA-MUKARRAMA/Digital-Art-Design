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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Transactional
@RestController
public class DesignerMyDesignsController {

    @Autowired
    private DesignerRepo designerRepo;

    @Autowired
    private DesignRepo designRepo;

    @Autowired
    private CategoryRepo categoryRepo;


    @GetMapping("/designerMyDesigns")
    public ResponseEntity<?> mydesign(@RequestParam Long id, Model m) {

        try {
            System.out.println(id);
            if (id == null ) {
                // Handle missing parameters
                return ResponseEntity.badRequest().body("Both adminId and password are required.");
            }

            DesignerD dsr = designerRepo.getOne(id);

            if(dsr!=null){
                Set<DesignD> dsns = designRepo.findAllByDesigner_Id(id);
//                Map<Long, Map<String, String>> ppData = new HashMap<>();
//                for (DesignD d : dsns) {
//                    byte[] pp = d.getContent();
//                    String base64Image = Base64.encodeBase64String(pp);
//                    System.out.println(base64Image);
//                    Optional<MediaType> mediaType = determineMediaType(base64Image);
//                    Map<String, String> temp = new HashMap<>();
//                    temp.put("base64", base64Image);
//                    temp.put("format", mediaType.toString());
//                    ppData.put(d.getDesign_Code(), temp);
//                }
                Map<Integer, String> ctData = new HashMap<>();
                for(DesignD d:dsns){
                    Integer ctgrCode = d.getCtgr_Code();
                    CategoryD ctgr = categoryRepo.getOne(ctgrCode);
                    ctData.put(ctgrCode,ctgr.getCtgr_Title());
                }

                m.addAttribute("designer", dsr);
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

    @PostMapping("/designerDeleteDesign")
    public ResponseEntity<?> deletedesign(@RequestParam Long id, @RequestParam(value = "design_code") Long design_code) {

        System.out.println(design_code);
        try {
            System.out.println(id);
            if (id == null ) {
                // Handle missing parameters
                return ResponseEntity.badRequest().body("Both adminId and password are required.");
            }

            DesignerD dsr = designerRepo.getOne(id);

            if(dsr!=null){
                Optional<DesignD> designDOptional = designRepo.findById(design_code);
                if (designDOptional.isPresent()) {
                    designRepo.deleteById(design_code);
                    return ResponseEntity.ok().body("Design deleted successfully.");
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Design not found.");
                }
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
