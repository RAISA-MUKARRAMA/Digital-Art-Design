package com.example.Digital.Art.Design.Rashu.Controller;

import com.example.Digital.Art.Design.Rashu.Repository.Rashu_DesignerRepository;
import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_Counter;
import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_Designer;
import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_User;
import com.example.Digital.Art.Design.Rashu.Repository.Rashu_CounterRepository;
import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_SignupRequest;
import com.example.Digital.Art.Design.Rashu.Repository.Rashu_UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping("/api")
public class Rashu_SignupController {
    @Autowired
    private Rashu_DesignerRepository designerRepository;
    @Autowired
    private Rashu_UserRepository userRepository;

    @Autowired
    private Rashu_CounterRepository counterRepository; // Inject the CounterRepository
    @Transactional // Add this annotation at the method level
    @PostMapping("/Rashu_signup")
    public ResponseEntity<String> signup(@RequestBody Rashu_SignupRequest req) {
        try {
            String signupAs = req.getSignupAs();

            Rashu_Counter counter = counterRepository.findById(0L).orElseGet(Rashu_Counter::new);

            if (signupAs.equals("Designer")) {
                // Handle designer signup
                long newId = counter.getDesigner_Id_curr() + 1;
                counter.setDesigner_Id_curr(newId);
                // Save the updated counter value
                counterRepository.save(counter); // Use the repository to save the counter

                Rashu_Designer designer = new Rashu_Designer(
                        req.getName(),
                        req.getEmail(),
                        req.getPhone(),
                        req.getPassword()
                );

                designer.setId(newId); // Set the designer's ID
                Rashu_Designer savedDesigner = designerRepository.save(designer);
                System.out.println(savedDesigner.getAdds());
                return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body("Designer : Your ID is " + savedDesigner.getId());
            } else if (signupAs.equals("User")) {
                // Handle user signup

                long newId = counter.getUser_Id_curr() + 1;
                counter.setUser_Id_curr(newId);
                // Save the updated counter value
                counterRepository.save(counter); // Use the repository to save the counter

                Rashu_User user = new Rashu_User(
                        req.getName(),
                        req.getEmail(),
                        req.getPhone(),
                        req.getPassword()
                );

                user.setId(newId); // Set the user's ID

                Rashu_User savedUser = userRepository.save(user);
                System.out.println(savedUser.getAdds());
                return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body("User: Your ID is " + savedUser.getId());



            } else {
                return ResponseEntity.badRequest().body("Invalid 'signupAs' value");
            }
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
