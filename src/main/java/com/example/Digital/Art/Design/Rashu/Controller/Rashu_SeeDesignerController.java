package com.example.Digital.Art.Design.Rashu.Controller;

import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_Designer;
import com.example.Digital.Art.Design.Rashu.Repository.Rashu_DesignerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Transactional
@RestController
@RequestMapping("/Rashu_designers")
public class Rashu_SeeDesignerController {

    @Autowired
    private Rashu_DesignerRepository designerRepository;

    @GetMapping("/list")
    public ResponseEntity<List<Rashu_Designer>> getDesignerList() {
        List<Rashu_Designer> designers = designerRepository.findAll();
        return ResponseEntity.ok(designers);
    }
}
