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
public class DesignerParticipateInContestController {

    @Autowired
    private DesignerRepo designerRepo;

    @Autowired
    private ContestRepo contestRepo;

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private DesignRepo designRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private IncludedInRepo includedInRepo;

    @GetMapping("/designerParticipateInContest")
    public ResponseEntity<?> participate(@RequestParam Long id, Model model) {
        if (id == null) {
            // Handle missing parameters
            return ResponseEntity.badRequest().body("ID required");
        }
        System.out.println(id);
        DesignerD dsr = designerRepo.findById(id).orElse(null);
        if (dsr != null) {
            List<ContestD> cst = contestRepo.findAll();

            List<AdminD> adm = adminRepo.findAll();

            List<UserD> usr = userRepo.findAll();

            List<DesignerD> dsrs = designerRepo.findAll();

            List<CategoryD> ctgr = categoryRepo.findAll();

            List<IncludedInD> incldin = includedInRepo.findAll();

            Set<DesignD> myDesigns = designRepo.findAllByDesigner_Id(id);

            Map<Long, Integer> m1 = new HashMap<>();
            for (ContestD c : cst) {
                List<Long> d = designRepo.findByContests_Contest_Code(c.getContest_Code());
                m1.put(c.getContest_Code(), d.size());
            }
            model.addAttribute("total", m1);
            model.addAttribute("contests", cst);
            model.addAttribute("admins", adm);
            model.addAttribute("users", usr);
            model.addAttribute("designers", dsrs);
            model.addAttribute("categories", ctgr);
            model.addAttribute("includedIn", incldin);
            model.addAttribute("myDesigns", myDesigns);
            return ResponseEntity.ok(model);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/designerParticipateInContest")
    public ResponseEntity<?> designerParticipateInContest(@RequestParam Long id,
                                                          @RequestParam(value = "selectedDesign") Long desgn_code,
                                                          @RequestParam(value="contest_code") Long contest_code){
        if (id == null) {
            // Handle missing parameters
            return ResponseEntity.badRequest().body("ID required");
        }
        System.out.println(id);
        DesignerD dsr = designerRepo.findById(id).orElse(null);

        if (dsr != null) {
//            ContestD contestD = contestRepo.getById(contest_code);
            DesignD designD = designRepo.getById(desgn_code);

//            IncludedInD includedInD = new IncludedInD();
//            includedInD.setContest(contestD);
//            includedInD.setDesign(designD);
//            includedInD.setSelection(null);
//
//            includedInRepo.save(includedInD);

            Optional<ContestD> optionalContest = contestRepo.findById(contest_code);
            ContestD cst = optionalContest.orElse(null);
            if (cst != null) {
                    designD.getContests().put(null, cst);
                    designRepo.save(designD);
            }

            return ResponseEntity.ok("Participated in the contest Successfully");
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

}
