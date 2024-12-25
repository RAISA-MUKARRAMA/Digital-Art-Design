package com.example.Digital.Art.Design.Rashu.Controller;

import com.example.Digital.Art.Design.Rashu.EntityClasses.*;
import com.example.Digital.Art.Design.Rashu.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Transactional
@RestController
@RequestMapping("/api")
public class Rashu_SetAContest {
    @Autowired
    private Rashu_AdminRepository adminRepository;

    @Autowired
    private Rashu_UserRepository userRepository;

    @Autowired
    private Rashu_ContestRepository contestRepository;

    @Autowired
    private Rashu_CounterRepository counterRepository; // Inject the CounterRepository

    @PostMapping("/Rashu_setacontest")
    public ResponseEntity<?> setAContest(@RequestBody Rashu_ContestSet contestSet) {
        try {
            if (contestSet.getId() == null || contestSet == null) {
                // Return an error response as JSON
                return ResponseEntity.badRequest().body(
                        "{\"status\":\"error\",\"message\":\"All form fields are required.\"}"
                );
            }

            Rashu_Admin admin = adminRepository.findById(contestSet.getId()).orElse(null);
            if (admin == null || !admin.getPass().equals(contestSet.getConfirmpassword())) {
                return ResponseEntity.badRequest()
                        .body("{\"status\":\"error\",\"message\":\"Wrong password\"}");
            }
            Rashu_User user = userRepository.findById(contestSet.getContestsUser()).orElse(null);
            // Your business logic here to process the contestSet object
            Rashu_Counter counter = counterRepository.findById(0L).orElseGet(Rashu_Counter::new);
            long newId = counter.getCnt_code() + 1;
            counter.setCnt_code(newId);

             Rashu_Contest contest = new Rashu_Contest();
             contest.setCnt_code(newId);
             contest.setCnt_Title(contestSet.contestTitle());
             contest.setStart(contestSet.contestsDate());
             contest.setLast(contestSet.contestLDate());
             contest.setDescription(contestSet.contestDescription());
             contest.setMxS(contestSet.maxSubmissions());
             contest.setAdmin(admin);
             contest.setUser(user);
             contestRepository.save(contest);
             counterRepository.save(counter);

            // Return a success response
            return ResponseEntity.ok().body("Contest set successfully.");
        } catch (Exception e) {
            // Handle server errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"status\":\"error\",\"message\":\"Error: " + e.getMessage() + "\"}");
        }
    }
}
