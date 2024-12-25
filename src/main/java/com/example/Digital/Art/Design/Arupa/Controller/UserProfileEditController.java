package com.example.Digital.Art.Design.Arupa.Controller;

import com.example.Digital.Art.Design.Arupa.EntityClasses.UserProfile;
import com.example.Digital.Art.Design.Arupa.Repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class UserProfileEditController {
    @Autowired
    UserProfileRepository userProfileRepository;

    @GetMapping("/editProfile/{userId}")
    public String editProfile(@PathVariable(value = "userId") Long id, Model model){
        UserProfile user = userProfileRepository.findById(id).orElse(null);
        model.addAttribute("user", user);
        return "/Arupa/user_edit_profile.html";
    }

    @PostMapping("/userEditProfile/{userId}")
    public String userEditProfile(@PathVariable(value = "userId") Long id, @RequestParam String name, @RequestParam String contact,
                                  @RequestParam String email, @RequestParam LocalDate dob, @RequestParam String adds,
                                  @RequestParam String bio, Model model){
        UserProfile userProfile = userProfileRepository.getOne(id);
        userProfile.setName(name);
        userProfile.setEmail(email);
        userProfile.setAdds(adds);
        userProfile.setBio(bio);
        userProfile.setContact(contact);
        userProfile.setDob(dob);

        userProfileRepository.save(userProfile);
        model.addAttribute("user", userProfile);
        return "redirect:/UserProfile/{userId}";
    }

    @PostMapping("/changepass/{userId}")
    public String changePass(@PathVariable(value = "userId") Long id,
                             @RequestParam String oldpass,
                             @RequestParam String newpass,
                             @RequestParam String confirmpass, Model model){
        UserProfile user = userProfileRepository.findById(id).orElse(null);
        String old = user.getPass();
        if(!old.equals(oldpass)){
            model.addAttribute("error", "Old password doesn't match");
            model.addAttribute("user", user);
            return "/Arupa/user_change_pass.html";
        }
        else if(!newpass.equals(confirmpass)){
            model.addAttribute("error", "New password and confirm password don't match");
            model.addAttribute("user", user);
            return "/Arupa/user_change_pass.html";
        }
        else if(newpass.length()!=8){
            model.addAttribute("error", "Password must have eight characters!");
            model.addAttribute("user", user);
            return "/Arupa/user_change_pass.html";
        }
        user.setPass(newpass);
        userProfileRepository.save(user);
        model.addAttribute("user", user);
        return "redirect:/UserProfile/{userId}";
    }

    @GetMapping("/password/{userId}")
    public String password(@PathVariable(value = "userId") Long id, Model model){
        UserProfile user = userProfileRepository.findById(id).orElse(null);
        model.addAttribute("user", user);
        return "/Arupa/user_change_pass.html";
    }
}
