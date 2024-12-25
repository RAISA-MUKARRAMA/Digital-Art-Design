package com.example.Digital.Art.Design.Rashu.Controller;

import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_Design;
import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_IncludedIn;
import com.example.Digital.Art.Design.Rashu.Repository.Rashu_DesignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

@Transactional
@Controller
@RequestMapping("/Rashu_seeContestDesigns")
public class Rashu_SeeContestDesigns {

    @Autowired
    private Rashu_DesignRepository designRepository;

    @Autowired
    private EntityManager entityManager;

    @GetMapping
    public ResponseEntity<List<Rashu_IncludedIn>> seeContestDesigns(@RequestParam Long contestId) {
        String jpql = "SELECT  i FROM Rashu_IncludedIn i WHERE i.contest.cnt_code = :contestId";

        Query query = entityManager.createQuery(jpql, Rashu_IncludedIn.class);
        query.setParameter("contestId", contestId);

        @SuppressWarnings("unchecked")
        List<Rashu_IncludedIn> contestDesigns = query.getResultList();

        return ResponseEntity.ok(contestDesigns);
    }
}
