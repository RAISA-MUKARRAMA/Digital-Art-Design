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
public class DesignerSeeDesignersController {

    @Autowired
    private DesignerRepo designerRepo;

    @Autowired
    private DesignRepo designRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @GetMapping("/designerGetDesignerList")
    public ResponseEntity<?> participate(@RequestParam Long id, Model model) {
        if (id == null) {
            // Handle missing parameters
            return ResponseEntity.badRequest().body("ID required");
        }
        System.out.println(id);
        DesignerD dsr = designerRepo.findById(id).orElse(null);
        if (dsr != null) {

            List<DesignerD> designers = designerRepo.findAll();

//            Long cntno = cntstRepo.contestHolded(adm.getAdmin_Id());
            Map<Long,Long> rate= new HashMap<>();
            Map<Long,Long> tdsn= new HashMap<>();
            Map<Long,Long> pcntst= new HashMap<>();
            Map<Long,Set<String>> itscategory= new HashMap<>();

            for(DesignerD dsnr:designers){
                Long did= dsnr.getDesignerId();

                //rating
                rate.put(did,designRepo.cntRating(did));

                //total designs
                tdsn.put(did,designRepo.cntDesign(did));

                //number of participated contests
                Set<DesignD> ddesigns = designRepo.findAllByDesigner_Id(did);
                Set<Long> ct = new HashSet<>();
                Set<Long> pct = new HashSet<>();
                for(DesignD d : ddesigns){
                    long dcode= d.getDesign_Code();
                    System.out.println(dcode);
                    ct = designRepo.findByContests_Design_Code(dcode);
                    for (Long ctt: ct){
                        pct.add(ctt.longValue());
                    }
                }
                pcntst.put(did, (long) pct.size());

                //category
                List<Integer> ctgrcd = designerRepo.findCategories(did);
                Set<String> ctgr = new HashSet<>();
                for (Integer i:ctgrcd){
                    CategoryD e = categoryRepo.getOne(i);
                    ctgr.add(e.getCtgr_Title());
                }
                itscategory.put(did,ctgr);
            }

            model.addAttribute("designers",designers);
            model.addAttribute("rating",rate);
            model.addAttribute("tdesigns",tdsn);
            model.addAttribute("nocontests",pcntst);
            model.addAttribute("itsCategory",itscategory);
            return ResponseEntity.ok(model);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
