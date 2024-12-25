package com.example.Digital.Art.Design.Raisa.controller;

import com.example.Digital.Art.Design.Raisa.model.CategoryD;
import com.example.Digital.Art.Design.Raisa.model.DesignD;
import com.example.Digital.Art.Design.Raisa.model.DesignerD;
import com.example.Digital.Art.Design.Raisa.repository.CategoryRepo;
import com.example.Digital.Art.Design.Raisa.repository.DesignRepo;
import com.example.Digital.Art.Design.Raisa.repository.DesignerRepo;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@RestController
@RequestMapping("/designerProfile")
public class DesignerProfilePageController {

    @Autowired
    private DesignerRepo designerRepo;

    @Autowired
    private DesignRepo designRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @GetMapping
    public ResponseEntity<?> getDesignerProfile(@RequestParam Long id, Model m) {
        System.out.println(id);
        if (id == null ) {
            // Handle missing parameters
            return ResponseEntity.badRequest().body("Both adminId and password are required.");
        }

        DesignerD designerD = designerRepo.findById(id).orElse(null);
        System.out.println(designerD);

        if (designerD != null) {
            byte[] ppData= designerD.getProfilePhoto();
            String base64Image = Base64.encodeBase64String(ppData);

            Long rate= designRepo.cntRating(id);
            Long tdsn = designRepo.cntDesign(id);
            Set<DesignD> ddesigns = new HashSet<>();
            ddesigns = designRepo.findAllByDesigner_Id(id);
//        System.out.println(ddesigns);
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
            List<Integer> ctgrcd = designerRepo.findCategories(id);
            Set<String> ctgr = new HashSet<>();
            for (Integer i:ctgrcd){
                CategoryD e = categoryRepo.getOne(i);
                ctgr.add(e.getCtgr_Title());
            }
            m.addAttribute("vote",rate);
            m.addAttribute("ctgrdesiger",ctgr);
            m.addAttribute("atncst",pct.size());
            m.addAttribute("totalDesigns",tdsn);
            m.addAttribute("designer", designerD);
            m.addAttribute("propic",base64Image);
            return ResponseEntity.ok(m);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
