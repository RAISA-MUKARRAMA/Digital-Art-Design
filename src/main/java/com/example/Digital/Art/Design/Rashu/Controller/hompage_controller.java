package com.example.Digital.Art.Design.Rashu.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Transactional
@Controller
public class hompage_controller {
    @GetMapping ("/home")
    public String home()
    {
        return "/Arupa/homepage.html";
    }
}
