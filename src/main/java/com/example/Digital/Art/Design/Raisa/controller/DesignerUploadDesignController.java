package com.example.Digital.Art.Design.Raisa.controller;

import com.example.Digital.Art.Design.Raisa.model.*;
import com.example.Digital.Art.Design.Raisa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
@RestController
@RequestMapping("/designerUploadDesign")
public class DesignerUploadDesignController {

    @Autowired
    private CounterRepo counterRepo;

    @Autowired
    private DesignRepo designRepo;

    @Autowired
    private DesignerRepo designerRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @PostMapping
    public ResponseEntity<?> submitted(@RequestParam Long id,
                                       @RequestParam(value = "selectedCtgr") String selectedCtgr,
                                       @RequestParam(value = "title") String title,
                                       @RequestParam(value = "des") String des,
                                       @RequestParam(value = "cont") MultipartFile cont,
                                       Model model) {

        if (id == null) {
            // Handle missing parameters
            return ResponseEntity.badRequest().body("ID required");
        }
        System.out.println(id);
        DesignerD dsr = designerRepo.findById(id).orElse(null);

        if (dsr != null) {
            DesignD dsn = new DesignD();
            long cntid = 0;
            CounterD cnt = counterRepo.getOne(cntid);
            cnt.setDesign_Code_curr(cnt.getDesign_Code_curr() + 1);
            counterRepo.save(cnt);
            dsn.setDesign_Code(cnt.getDesign_Code_curr());

//            Long dsncode = dsn.getDesign_Code();

            int sctgr = -1;
            if (!selectedCtgr.isEmpty()) {
                sctgr = Integer.parseInt(selectedCtgr);
                System.out.println(sctgr);
                dsn.setCtgr_Code(sctgr);
            }
            System.out.println(sctgr);
            if (!title.isEmpty()) {
                dsn.setDesign_Title(title);
            }

            if (!des.isEmpty()) {
                dsn.setDescription(des);
            }
            dsn.setRating(0);

            try {
                if (!cont.isEmpty()) {
                    byte[] bcont = cont.getBytes();
                    dsn.setContent(bcont);
                    // Now you have the content of the file as a byte array (fileBytes)
                    // Your handling logic using the byte array
                } else {
                    // Handle the case when no file is uploaded
                    return ResponseEntity.badRequest().body("Empty file uploaded.");
                }
            } catch (IOException e) {
                // Handle the IOException
                return ResponseEntity.badRequest().body("Failed to read the file.");
            }

            // Move the designer information setting and repository save outside the try-catch block
            dsn.setDesigner_Id(id);
            designRepo.save(dsn);

            //update works for
            List<Integer> ctgr = designerRepo.findCategories(id);
            if (!ctgr.contains(sctgr)) {
                DesignerD dsnr = designerRepo.getOne(id);
                CategoryD ctg = categoryRepo.getOne(sctgr);
                dsnr.getCategories().add(ctg);
                designerRepo.save(dsr);
            }
            return ResponseEntity.ok("Design Uploaded Successfully");
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
