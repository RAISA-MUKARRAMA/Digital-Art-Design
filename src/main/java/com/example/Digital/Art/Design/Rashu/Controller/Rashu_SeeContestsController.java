package com.example.Digital.Art.Design.Rashu.Controller;

import com.example.Digital.Art.Design.Rashu.EntityClasses.*;
import com.example.Digital.Art.Design.Rashu.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
@Transactional
@RestController
@RequestMapping("/Rashu_contest")
public class Rashu_SeeContestsController {
    @Autowired
    private Rashu_ContestRepository contestRepository;

    @GetMapping("/list")
    public ResponseEntity<List<Rashu_Contest>> getContestList(@RequestParam("adminId") Long adminId) {
        List<Rashu_Contest> contestList = contestRepository.findByAdminId(adminId);


        contestList.sort(Comparator.comparing(Rashu_Contest::getLast, Comparator.nullsLast(Comparator.reverseOrder())));
        for (Rashu_Contest contest : contestList) {
            System.out.println("Ldate: " + contest.getLast());
        }


        return ResponseEntity.ok(contestList);
    }

    @GetMapping("/list/all")
    public ResponseEntity<List<Rashu_Contest>> getAllContests() {
        List<Rashu_Contest> allContests = contestRepository.findAll();
        allContests.sort(Comparator.comparing(Rashu_Contest::getLast, Comparator.nullsLast(Comparator.reverseOrder())));
        return ResponseEntity.ok(allContests);
    }
    @GetMapping // This maps to the base URL: "/contest"
    public ResponseEntity<Rashu_Contest> getContestById(@RequestParam Long contestId) {
        // Fetch the contest by its ID
        Rashu_Contest contest = contestRepository.findById(contestId).orElse(null);

        if (contest == null) {
            // Handle the case when the contest is not found, e.g., return a 404 status
            return ResponseEntity.notFound().build();

        }
        System.out.println(contestId);

        // Return the contest data as a response
        return ResponseEntity.ok(contest);
    }
}
