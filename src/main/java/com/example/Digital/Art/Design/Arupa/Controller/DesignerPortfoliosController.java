package com.example.Digital.Art.Design.Arupa.Controller;

import com.example.Digital.Art.Design.Arupa.EntityClasses.*;
import com.example.Digital.Art.Design.Arupa.Repository.*;
import com.example.Digital.Art.Design.Raisa.model.CategoryD;
import com.example.Digital.Art.Design.Raisa.model.DesignD;
import com.example.Digital.Art.Design.Raisa.model.DesignerD;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.*;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DesignerPortfoliosController {
    @Autowired
    UDesignerRepository designerRepository;

    @Autowired
    UserDesignRepository designRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserOrderRepository uor;

    @Autowired
    UserContestRepository ucr;

    @GetMapping("/designer_list/{userId}")
    public String getAllDesigners(@PathVariable(value = "userId") Long userId, Model model) {
        List<UserDesigner> designers = designerRepository.findAll();
        UserProfile user = userProfileRepository.findById(userId).orElse(null);
        model.addAttribute("designers", designers);
        model.addAttribute("user", user);
        return "/Arupa/user_designer_list.html";
    }

    @GetMapping("/completedOrders/{userId}")
    public String allDesigns(@PathVariable(value = "userId") Long userId, Model model)
    {
        List<Udesign> ualld = new ArrayList<>();
        List<UserOrder> orders = uor.findByuId(userId);
        UserProfile user = userProfileRepository.findById(userId).orElse(null);
        model.addAttribute("user", user);
        for(UserOrder order:orders){
            Long dcode = order.getDesign_code();
            if(dcode!=null){
                Udesign design = designRepository.findById(dcode).orElse(null);
                ualld.add(design);
            }
        }
        if(!ualld.isEmpty()){
            Map<Long, Map<String,String>> ppData = new HashMap<>();
            Map<Long,String> udesignername = new HashMap<>();
            for (Udesign d:ualld){
                byte[] pp = d.getContent();
//            System.out.println(pp);
                String base64Image = Base64.encodeBase64String(pp);
                System.out.println(base64Image);
                Optional<MediaType> mediaType = determineMediaType(base64Image);
//            System.out.println(mediaType);
                Map<String,String> temp = new HashMap<>();
                temp.put("base64",base64Image);
                temp.put("format",mediaType.toString());
                ppData.put(d.getDesign_code(),temp);

                //finding designer name
                UserDesigner designerD = designerRepository.getOne(d.getDesigner_id());
                udesignername.put(d.getDesign_code(),designerD.getName());
            }
            model.addAttribute("content",ppData);
            model.addAttribute("designerName",udesignername);
            model.addAttribute("alldesigns",ualld);
        }
        else{
            model.addAttribute("message", "No completed orders to show.");
        }
        return "/Arupa/User_Designs.html";
    }


    @GetMapping("/giveLike/{designcode}/{userId}")
    public String givelike(@PathVariable("designcode") Long dsCode,
                           @PathVariable(value = "userId") Long userId, Model model)
    {
        Udesign udesign = designRepository.getOne(dsCode);
        UserProfile user = userProfileRepository.findById(userId).orElse(null);
        Set<UserProfile> users = udesign.getUsers();
        if (users.contains(user)) {
            // User has already liked the design, so unlike it
            users.remove(user);
            udesign.setRating(udesign.getRating() - 1);
            user.getLikedDesigns().remove(udesign); // Remove the design from user's liked designs
        } else {
            // User hasn't liked the design yet, so like it
            users.add(user);
            udesign.setRating(udesign.getRating() + 1);
            user.getLikedDesigns().add(udesign); // Add the design to user's liked designs
        }

        // Save the changes to the Udesign entity
        udesign.setUsersWhoLiked(users); // Update the users set
        designRepository.save(udesign);

        // Save the changes to the UserProfile entity
        userProfileRepository.save(user);

        return "redirect:/completedOrders/{userId}";
    }

    private Optional<MediaType> determineMediaType(String fileContent) {
        // Use MediaTypeFactory to determine media type based on file content
        Optional<MediaType> mediaType = MediaTypeFactory.getMediaType(fileContent);
        return mediaType;
    }

    @GetMapping("/uDesignerProfile/{designerId}/{userId}")
    public String designerProf(@PathVariable(value = "designerId") Long designerId,
                               @PathVariable(value = "userId") Long userId, Model model){
        UserProfile user = userProfileRepository.findById(userId).orElse(null);
        model.addAttribute("user", user);
        UserDesigner dsr = designerRepository.getOne(designerId);
        Set<UserCategory> categories = dsr.getwCategories();

        List<Udesign> all = designRepository.findAll();
        List<Udesign> designs = new ArrayList<>();
        for(Udesign design:all){
            if(!Objects.equals(design.getDesigner_id(), designerId)) {
                continue;
            }
            designs.add(design);
        }
        if(!designs.isEmpty()){
            model.addAttribute("designs", designs);
            long completed = designs.size();
            model.addAttribute("completed", completed);
        }
        else{
            long completed = 0;
            model.addAttribute("completed", completed);
        }
        long contests = 0;
        for(Udesign design: designs){
            Set<UserContest> contest = design.getiContests();
            if(!contest.isEmpty()){
                contests += 1;
            }
        }

        List<UserOrder> oAll = uor.findAll();
        List<UserOrder> orders = new ArrayList<>();
        for(UserOrder u:oAll){
            if(!Objects.equals(u.getD_id(), designerId)){
                continue;
            }
            orders.add(u);
        }
        if(!orders.isEmpty()){
            long totalOrders = orders.size();
            model.addAttribute("numOfOrders", totalOrders);
        }
        else{
            long total = 0;
            model.addAttribute("numOfOrders", total);
        }
        byte[] ppData= dsr.getProfilePhoto();
        String base64Image = Base64.encodeBase64String(ppData);

        model.addAttribute("designer", dsr);
        model.addAttribute("propic",base64Image);
        model.addAttribute("categories", categories);
        model.addAttribute("contests", contests);
        return "/Arupa/uDesignerProfile.html";
    }

    @GetMapping("/designsOfDesigner/{designerId}/{userId}")
    String deisgnsOfDesigner(@PathVariable(value = "designerId") Long designerId,
                             @PathVariable(value = "userId") Long userId, Model model){
        UserDesigner designer = designerRepository.findById(designerId).orElse(null);
        UserProfile user = userProfileRepository.findById(userId).orElse(null);
        List<Udesign> designs = designRepository.findAllBydesigner_id(designerId);
        if(!designs.isEmpty()){
            Map<Long, Map<String, String>> ppData = new HashMap<>();
            for(Udesign d:designs){
                byte[] pp = d.getContent();
                String base64Image = Base64.encodeBase64String(pp);
                System.out.println(base64Image);
                Optional<MediaType> mediaType = determineMediaType(base64Image);
                Map<String,String> temp = new HashMap<>();
                temp.put("base64",base64Image);
                temp.put("format",mediaType.toString());
                ppData.put(d.getDesign_code(),temp);
            }
            model.addAttribute("content", ppData);
            model.addAttribute("DSN", designs);
        }
        else{
            model.addAttribute("message", "No designs to show.");
        }
        model.addAttribute("user", user);
        model.addAttribute("designer", designer);
        return "/Arupa/designsOfDesigner.html";
    }

    @GetMapping("/designsLike/{designId}/{userId}/{designerId}")
    String designLike(@PathVariable(value = "designId") Long dsCode,
                     @PathVariable(value = "userId")Long userId,
                      @PathVariable(value = "designerId")Long designerId,Model model){

        Udesign udesign = designRepository.getOne(dsCode);
        UserProfile user = userProfileRepository.findById(userId).orElse(null);
        Set<UserProfile> users = udesign.getUsers();
        if (users.contains(user)) {
            // User has already liked the design, so unlike it
            users.remove(user);
            udesign.setRating(udesign.getRating() - 1);
            user.getLikedDesigns().remove(udesign); // Remove the design from user's liked designs
        } else {
            // User hasn't liked the design yet, so like it
            users.add(user);
            udesign.setRating(udesign.getRating() + 1);
            user.getLikedDesigns().add(udesign); // Add the design to user's liked designs
        }

        // Save the changes to the Udesign entity
        udesign.setUsersWhoLiked(users); // Update the users set
        designRepository.save(udesign);

        // Save the changes to the UserProfile entity
        userProfileRepository.save(user);
        return "redirect:/designsOfDesigner/{designerId}/{userId}";
    }

    @GetMapping("/prevContest/{userId}")
    public String prevContest(@PathVariable(value = "userId")Long userId, Model model){
        List<UserContest> all = ucr.findByuserId(userId);
        List<UserContest> prev = new ArrayList<>();
        for(UserContest c:all){
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime last = c.getLast();
            int compare = last.compareTo(now);
            if(compare<0){
                prev.add(c);
            }
        }
        if(!prev.isEmpty()){
            model.addAttribute("contests", prev);
        }
        else{
            model.addAttribute("message", "No previous contests to show.");
        }
        UserProfile user = userProfileRepository.findById(userId).orElse(null);
        model.addAttribute("user", user);
        return "/Arupa/user_my_contest.html";
    }

    @GetMapping("/runningContest/{userId}")
    public String runningContest(@PathVariable(value = "userId")Long userId, Model model){
        List<UserContest> all = ucr.findByuserId(userId);
        List<UserContest> running = new ArrayList<>();
        for(UserContest c:all){
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime last = c.getLast();
            LocalDateTime start = c.getStart();
            int compare = last.compareTo(now);
            int compare2 = start.compareTo(now);
            if(compare2==0 || (compare2<0 && compare>0)) {
                running.add(c);
            }
        }
        if(!running.isEmpty()){
            model.addAttribute("contests", running);
        }
        else{
            model.addAttribute("message", "No running contests to show.");
        }
        UserProfile user = userProfileRepository.findById(userId).orElse(null);
        model.addAttribute("user", user);
        return "/Arupa/user_my_contest.html";
    }

    @GetMapping("/contestDesigns/{contestId}/{userId}")
    public String contestDesigns(@PathVariable(value = "contestId")Long contestId,
                                 @PathVariable(value = "userId")Long userId, Model model){
        UserContest contest = ucr.findById(contestId).orElse(null);
        Set<Udesign> designs = contest.getiDesigns();
        if(designs.isEmpty()){
            model.addAttribute("message", "No designs are submitted");
        }
        else{
            Map<Long, Map<String,String>> ppData = new HashMap<>();
            Map<Long,String> udesignername = new HashMap<>();
            for (Udesign d:designs){
                byte[] pp = d.getContent();
//            System.out.println(pp);
                String base64Image = Base64.encodeBase64String(pp);
                System.out.println(base64Image);
                Optional<MediaType> mediaType = determineMediaType(base64Image);
//            System.out.println(mediaType);
                Map<String,String> temp = new HashMap<>();
                temp.put("base64",base64Image);
                temp.put("format",mediaType.toString());
                ppData.put(d.getDesign_code(),temp);

                //finding designer name
                UserDesigner designerD = designerRepository.getOne(d.getDesigner_id());
                udesignername.put(d.getDesign_code(),designerD.getName());
            }
            model.addAttribute("content",ppData);
            model.addAttribute("designerName",udesignername);
            model.addAttribute("alldesigns",designs);
        }
        UserProfile user = userProfileRepository.findById(userId).orElse(null);
        model.addAttribute("user", user);
        model.addAttribute("contestId", contestId);
        return "/Arupa/designsOfContest.html";
    }

    @GetMapping("/contestLike/{designcode}/{userId}/{contestId}")
    public String contestlike(@PathVariable("designcode") Long dsCode,
                           @PathVariable(value = "userId") Long userId)
    {
        Udesign udesign = designRepository.getOne(dsCode);
        UserProfile user = userProfileRepository.findById(userId).orElse(null);
        Set<UserProfile> users = udesign.getUsers();
        if (users.contains(user)) {
            // User has already liked the design, so unlike it
            users.remove(user);
            udesign.setRating(udesign.getRating() - 1);
            user.getLikedDesigns().remove(udesign); // Remove the design from user's liked designs
        } else {
            // User hasn't liked the design yet, so like it
            users.add(user);
            udesign.setRating(udesign.getRating() + 1);
            user.getLikedDesigns().add(udesign); // Add the design to user's liked designs
        }

        // Save the changes to the Udesign entity
        udesign.setUsersWhoLiked(users); // Update the users set
        designRepository.save(udesign);

        // Save the changes to the UserProfile entity
        userProfileRepository.save(user);

        return "redirect:/contestDesigns/{contestId}/{userId}";
    }
}
