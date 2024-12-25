package com.example.Digital.Art.Design.Rashu.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Transactional
@Controller
@RequestMapping ("/Admin")
public class Rashu_HomeController {
    @GetMapping ("/MyProfile")
    public String admin_my_profile()
    {
        return "Admin_profile_my_profile";
    }

   @GetMapping("/AdminList")
    public String See_Admin()
   {
       return "Admin_profile_Admins";
   }

    @GetMapping("/AdminDetails")
    public String See_Admin_Details()
    {
        return "Admin_profile_Admins_Details.html";
    }

    @GetMapping("/Previous_Announcement")
    public String See_previous_announcement()
    {
        return "Admin_profile_Announcement_previous_announcement.html";
    }

    @GetMapping("/Set_An_Announcement")
    public String Set_an__announcement()
    {
        return "Admin_profile_Announcement_Set_an__announcement.html";
    }
    @GetMapping("/Catagories")
    public String See_Designs()
    {
        return "Admin_profile_Designs.html";
    }
    @GetMapping("/DesignerList")
    public String See_Designers()
    {
        return "Admin_profile_Designers.html";
    }
    @GetMapping("/Designers_Details")
    public String Designers_Details()
    {
        return "Admin_profile_Designers_Details.html";
    }
    @GetMapping("/Change_password")
    public String Change_password()
    {
        return "Admin_profile_Edit_Profile_Change_password.html";
    }

    @GetMapping("/Forgot_password")
    public String Forgot_password()
    {
        return "Admin_profile_Edit_Profile_Forgot_password.html";
    }
    @GetMapping("/Update_details")
    public String update_details()
    {
        return "Admin_profile_Edit_Profile_update_details.html";
    }
    @GetMapping("/FAQ")
    public String FAQ()
    {
        return "Admin_profile_FAQ.html";
    }
    @GetMapping("/About_Us")
    public String About_Us()
    {
        return "Admin_profile_About_Us.html";
    }
    @GetMapping("/Inbox")
    public String Inbox()
    {
        return "Admin_profile_Inbox.html";
    }
    @GetMapping("Contest_request")
    public String contest_request()
    {
        return "Admin_profile_Mycontest_contest_request.html";
    }
    @GetMapping("/Previous_contest")
    public String previous_contest()
    {
        return "Admin_profile_Mycontest_previous_contest.html";
    }
    @GetMapping("/Contest_Design_list")
    public String contest_Design_list()
    {
        return "Admin_profile_Mycontest_previous_contest_Design_list.html";
    }

    @GetMapping("/Set_A_Contest")
    public String Set_A_Contest()
    {
        return "Admin_profile_Mycontest_Set_A_Contest.html";
    }
    @GetMapping("/Our_Services")
    public String Our_Services()
    {
        return "Admin_profile_Our_Services.html";
    }
    @GetMapping("/User_Details")
    public String User_Details()
    {
        return "Admin_profile_User_Details.html";
    }
    @GetMapping("/UserList")
    public String See_User()
    {
        return "Admin_profile_Users.html";
    }


}
