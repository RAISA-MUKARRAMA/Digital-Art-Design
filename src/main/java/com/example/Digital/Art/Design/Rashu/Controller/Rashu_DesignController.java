package com.example.Digital.Art.Design.Rashu.Controller;

import com.example.Digital.Art.Design.Rashu.Service.DesignService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.Digital.Art.Design.Rashu.Repository.*;
import com.example.Digital.Art.Design.Rashu.EntityClasses.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Transactional
@RestController
@RequestMapping("/Rashu_designs")
public class Rashu_DesignController {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private DesignService designService;
    @GetMapping("/list/{designer_id}")
    public ResponseEntity<List<Rashu_Design>> getDesignsByDesignerId(@PathVariable Long designer_id) {
        List<Rashu_Design> designs = designService.getDesignsByDesignerId(designer_id);
//        System.out.println("gfgfg");
        if (!designs.isEmpty()) {
//            System.out.println("gfhghgfg");
            return ResponseEntity.ok().body(designs);
        } else {

            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/list/all")
    public ResponseEntity<List<Rashu_Design>> getAllDesigns() {
        List<Rashu_Design> designs = designService.findAllDesigns();

        if (!designs.isEmpty()) {
            return ResponseEntity.ok().body(designs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("/list/categories/{ctgr_Title}")
    public ResponseEntity<List<Rashu_Design>> getDesigns(@PathVariable String ctgr_Title) {

        String jpql = "SELECT c FROM Rashu_Design c WHERE c.category.ctgr_Title = :ctgr_Title";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("ctgr_Title", ctgr_Title);
        List<Rashu_Design> categories = query.getResultList();

        if (!categories.isEmpty()) {
            System.out.println("Category found");
            return ResponseEntity.ok().body(categories);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }


    @GetMapping("/orders/{user_id}")
    public ResponseEntity<List<Rashu_Order>> getOrderByUserId(@PathVariable Long user_id) {
        String jpql = "SELECT c FROM Rashu_Order c WHERE c.user.id = :user_id";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("user_id", user_id);
        List<Rashu_Order> orders = query.getResultList();

        if (!orders.isEmpty()) {
            System.out.println("Category found");
            return ResponseEntity.ok().body(orders);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/list/designer-and-category/{designer_id}/{ctgr_Title}")
    public ResponseEntity<List<Rashu_Design>> getDesignsByDesignerIdAndCategory(
            @PathVariable Long designer_id,
            @PathVariable String ctgr_Title){

        String jpql = "SELECT c FROM Rashu_Design c WHERE c.designer.id = :designer_id AND c.category.ctgr_Title = :ctgr_Title";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("designer_id", designer_id);
        query.setParameter("ctgr_Title", ctgr_Title);
        List<Rashu_Design> designs = query.getResultList();

        if (!designs.isEmpty()) {
            return ResponseEntity.ok().body(designs);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
    @GetMapping("/showlist")
    public ResponseEntity<List<Rashu_Category>> getAllCategories() {

        String jpql = "SELECT c FROM Rashu_Category c";
        Query query = entityManager.createQuery(jpql);
        List<Rashu_Category> categories = query.getResultList();

        if (!categories.isEmpty()) {
            return ResponseEntity.ok().body(categories);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Autowired
    private Rashu_DesignRepository rashu_DesignRepository;


    @PostMapping("/like/{designCode}")
    public ResponseEntity<?> likeDesign(@PathVariable Long designCode, @RequestParam(required = false) Integer ratingChange) {
        try {
            Optional<Rashu_Design> optionalDesign = rashu_DesignRepository.findById(designCode);

            if (optionalDesign.isPresent()) {
                Rashu_Design design = optionalDesign.get();

                // If rating is null, start from zero
                if (design.getRating() == null) {
                    design.setRating(0);
                }

                // Check if ratingChange is provided and update the rating accordingly
                if (ratingChange != null) {
                    design.setRating(design.getRating() + ratingChange);
                    rashu_DesignRepository.save(design);
                    return ResponseEntity.ok("Rating updated successfully");
                } else {
                    return ResponseEntity.badRequest().body("Missing ratingChange parameter");
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating rating");
        }
    }
}
