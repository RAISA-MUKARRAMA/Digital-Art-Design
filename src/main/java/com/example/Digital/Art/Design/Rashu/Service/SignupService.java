package com.example.Digital.Art.Design.Rashu.Service;

import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_Counter;
import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_Designer;
import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_SignupRequest;
import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_User;
import com.example.Digital.Art.Design.Rashu.Repository.Rashu_CounterRepository;
import com.example.Digital.Art.Design.Rashu.Repository.Rashu_DesignerRepository;
import com.example.Digital.Art.Design.Rashu.Repository.Rashu_UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SignupService {
    @Autowired
    private Rashu_DesignerRepository designerRepository;

    @Autowired
    private Rashu_UserRepository userRepository;

    @Autowired
    private Rashu_CounterRepository counterRepository;

    @Transactional
    public String processSignup(Rashu_SignupRequest req, String signupAs) {
        Rashu_Counter counter = counterRepository.findById(0L).orElseGet(Rashu_Counter::new);

        if ("Designer".equals(signupAs)) {
            // Handle designer signup
            long newId = counter.getDesigner_Id_curr() + 1;
            counter.setDesigner_Id_curr(newId);
            counterRepository.save(counter);

            Rashu_Designer designer = new Rashu_Designer(
                    req.getName(),
                    req.getEmail(),
                    req.getPhone(),
                    req.getPassword()
            );
            designer.setId(newId);
            designerRepository.save(designer);

            return "Designer: Your ID is " + designer.getId();
        } else if ("User".equals(signupAs)) {
            // Handle user signup
            long newId = counter.getUser_Id_curr() + 1;
            counter.setUser_Id_curr(newId);
            counterRepository.save(counter);

            Rashu_User user = new Rashu_User(
                    req.getName(),
                    req.getEmail(),
                    req.getPhone(),
                    req.getPassword()
            );
            user.setId(newId);
            userRepository.save(user);

            return "User: Your ID is " + user.getId();
        } else {
            return "Invalid 'signupAs' value";
        }
    }
}
