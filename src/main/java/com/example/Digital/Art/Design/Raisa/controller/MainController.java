//package com.example.Digital.Art.Design.Raisa.controller;
//
//import com.example.Digital.Art.Design.Raisa.model.*;
//import com.example.Digital.Art.Design.Raisa.repository.*;
//import org.antlr.v4.runtime.misc.Pair;
//import org.apache.commons.codec.Resources;
//import org.apache.commons.codec.binary.Base64;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
//import org.springframework.http.MediaType;
//import org.springframework.http.MediaTypeFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.*;
//
//@ControllerAdvice
//@Controller
//public class MainController {
//    @Autowired
//    DesignerRepo dsrRepo;
//    @Autowired
//    CategoryRepo ctgrRepo;
//    @Autowired
//    DesignRepo dsnRepo;
//    @Autowired
//    CounterRepo cntRepo;
//    @Autowired
//    ContestRepo cntstRepo;
//    @Autowired
//    AdminRepo admRepo;
//    @Autowired
//    OrderRepo ordRepo;
//    @Autowired
//    UserRepo usrRepo;
//    @Autowired
//    AnnouncementRepo annRepo;
//
//    @GetMapping ("/signindesigner/{id}")
//    public String signin(@PathVariable(value = "id") long designerId, Model m){
//        DesignerD dsr = dsrRepo.getOne(designerId);
//        byte[] ppData= dsr.getProfilePhoto();
//        String base64Image = Base64.encodeBase64String(ppData);
//        Long rate= dsnRepo.cntRating(designerId);
//        Long tdsn = dsnRepo.cntDesign(designerId);
//        Set<DesignD> ddesigns = new HashSet<>();
//        ddesigns = dsnRepo.findAllByDesigner_Id(designerId);
////        System.out.println(ddesigns);
//        Set<Long> ct = new HashSet<>();
//        Set<Long> pct = new HashSet<>();
//        for(DesignD d : ddesigns){
//            long dcode= d.getDesign_Code();
//            System.out.println(dcode);
//            ct = dsnRepo.findByContests_Design_Code(dcode);
//            for (Long ctt: ct){
//                pct.add(ctt.longValue());
//            }
//        }
//        List<Integer> ctgrcd = dsrRepo.findCategories(designerId);
//        Set<CategoryD> ctgr = new HashSet<>();
//        for (Integer i:ctgrcd){
//            CategoryD e = ctgrRepo.getOne(i);
//            ctgr.add(e);
//        }
//        m.addAttribute("vote",rate);
//        m.addAttribute("ctgrdesiger",ctgr);
//        m.addAttribute("atncst",pct.size());
//        m.addAttribute("totalDesigns",tdsn);
//        m.addAttribute("designer", dsr);
//        m.addAttribute("propic",base64Image);
//        return "/Raisa/designerProfile.html";
//    }
//    @GetMapping("/uploaddesign/{designerId}")
//    public String uploadDesign(@PathVariable("designerId") long designerId,Model m,Model m1)
//    {
//        System.out.println("category " + designerId);
//        List<CategoryD> ctr = ctgrRepo.findAll();
//        DesignerD dsr = dsrRepo.getOne(designerId);
//        System.out.println(ctr);
//        m.addAttribute("categories",ctr);
//        m1.addAttribute("designer",dsr);
//        return "/Raisa/upload-design.html";
//    }
//    @PostMapping("/submitted/{designerId}")
//    public String submitted(@PathVariable("designerId") long designerId,
//                            @RequestParam(value = "selectedCtgr") String selectedCtgr,
//                            @RequestParam(value = "conId") String conId,
//                            @RequestParam(value = "ordId") String ordId,
//                            @RequestParam(value = "title") String title, @RequestParam(value = "des") String des,
//                            @RequestParam(value = "cont") MultipartFile cont, Model model) {
//        DesignD dsn = new DesignD();
//        long cntid = 0;
//        CounterD cnt = cntRepo.getOne(cntid);
//        cnt.setDesign_Code_curr(cnt.getDesign_Code_curr() + 1);
//        cntRepo.save(cnt);
//        dsn.setDesign_Code(cnt.getDesign_Code_curr());
//        Long dsncode = dsn.getDesign_Code();
//        int sctgr = Integer.parseInt(selectedCtgr);
//        dsn.setCtgr_Code(sctgr);
//        dsn.setDesign_Title(title);
//        dsn.setDescription(des);
//
//        try {
//            if (!cont.isEmpty()) {
//                byte[] bcont = cont.getBytes();
//                dsn.setContent(bcont);
//                // Now you have the content of the file as a byte array (fileBytes)
//                // Your handling logic using the byte array
//            } else {
//                // Handle the case when no file is uploaded
//                return "Empty file uploaded.";
//            }
//        } catch (IOException e) {
//            // Handle the IOException
//            return "Failed to read the file.";
//        }
//
//        // Move the designer information setting and repository save outside the try-catch block
//        dsn.setDesigner_Id(designerId);
//        dsnRepo.save(dsn);
//
//        if (conId != null && !conId.isEmpty()) {
//            long cstID = Long.parseLong(conId);
//            Optional<ContestD> optionalContest = cntstRepo.findById(cstID);
//            ContestD cst = optionalContest.orElse(null);
//            if (cst != null) {
//                LocalDateTime now = LocalDateTime.now();
//                if (cst.getLast_Date_of_Sub().compareTo(now) >= 0) {
//                    dsn.getContests().put(0, cst);
//                    dsnRepo.save(dsn);
//                }
//            }
//        }
////        if (ordId != null && !ordId.isEmpty()) {
////            long ordID = Long.parseLong(ordId);
////            Optional<OrderD> optionalOrderD = ordRepo.findById(ordID);
////            OrderD ord = optionalOrderD.orElse(null);
////            if (ord != null) {
////                ord.setDesign_code(cnt.getDesign_Code_curr());
////                ordRepo.save(ord);
////            }
////        }
//        //update works for
//        List<Integer> ctgr= dsrRepo.findCategories(designerId);
//        if(!ctgr.contains(sctgr)){
//            DesignerD dsr = dsrRepo.getOne(designerId);
//            CategoryD ctg  = ctgrRepo.getOne(sctgr);
//            dsr.getCategories().add(ctg);
//            dsrRepo.save(dsr);
//        }
//        return "redirect:/uploaddesign/{designerId}";
//    }
//
//    @GetMapping("/participate/{designerId}")
//    public String participate(@PathVariable("designerId") long designerId,Model m){
//        DesignerD designerD = dsrRepo.getOne(designerId);
//        List<ContestD> cst = cntstRepo.findRunning();
//        List<AdminD> adm = admRepo.findAll();
//        Map<Long,Integer> m1= new HashMap<>();
//        for(ContestD c:cst){
//            List<Long> d = dsnRepo.findByContests_Contest_Code(c.getContest_Code());
//            m1.put(c.getContest_Code(),d.size());
//        }
//        m.addAttribute("total",m1);
//        m.addAttribute("contests",cst);
//        m.addAttribute("admins",adm);
//        m.addAttribute("designer",designerD);
//        return "/Raisa/participate.html";
//    }
//
//    @GetMapping("/pastContests/{designerId}")
//    public String pastContests(@PathVariable("designerId") long designerId,Model m){
//        DesignerD designerD = dsrRepo.getOne(designerId);
//        List<ContestD> cst = cntstRepo.findPast();
//        List<AdminD> adm = admRepo.findAll();
//        Map<Long,Integer> m1= new HashMap<>();
//        for(ContestD c:cst){
//            List<Long> d = dsnRepo.findByContests_Contest_Code(c.getContest_Code());
//            m1.put(c.getContest_Code(),d.size());
//        }
//        m.addAttribute("total",m1);
//        m.addAttribute("contests",cst);
//        m.addAttribute("admins",adm);
//        m.addAttribute("designer",designerD);
//        return "/Raisa/pastContest.html";
//    }
//
//    @GetMapping("/upcoming/{designerId}")
//    public String upcoming(@PathVariable("designerId") long designerId,Model m){
//        DesignerD designerD = dsrRepo.getOne(designerId);
//        List<ContestD> cst = cntstRepo.findUpcoming();
//        List<AdminD> adm = admRepo.findAll();
//        Map<Long,Integer> m1= new HashMap<>();
//        for(ContestD c:cst){
//            List<Long> d = dsnRepo.findByContests_Contest_Code(c.getContest_Code());
//            m1.put(c.getContest_Code(),d.size());
//        }
//        m.addAttribute("total",m1);
//        m.addAttribute("contests",cst);
//        m.addAttribute("admins",adm);
//        m.addAttribute("designer",designerD);
//        return "/Raisa/upcomingContest.html";
//    }
//
//    @GetMapping("/myorders/{designerId}")
//    public String myorders(@PathVariable("designerId") long designerId,Model m)
//    {
//        DesignerD dsr = dsrRepo.getOne(designerId);
//        List<OrderD> o= ordRepo.findByDesignerId(designerId);
//        Map<Long,Long> md = new HashMap<>();
//        for(OrderD or : o){
//            System.out.println(or);
//        }
//
//        m.addAttribute("ords",o);
//        m.addAttribute("designer",dsr);
//        return "/Raisa/orders.html";
//    }
//
//    @GetMapping("/seeDsrs/{designerId}")
//    public String seeDesigners(@PathVariable("designerId") long designerId,Model m)
//    {
//        List<DesignerD> alld = dsrRepo.findAll();
//        DesignerD d= dsrRepo.getOne(designerId);
//        m.addAttribute("alld",alld);
//        m.addAttribute("designer",d);
//        return "/Raisa/seeDesigners.html";
//    }
//
//    @GetMapping("/DesignerDetails/{designerId}/{allid}")
//    public String DesignerDetails(@PathVariable(value = "designerId") long designerId,@PathVariable(value = "allid") long allid, Model m){
//        DesignerD dsr = dsrRepo.getOne(allid);
//        byte[] ppData= dsr.getProfilePhoto();
//        String base64Image = Base64.encodeBase64String(ppData);
//        Long rate= dsnRepo.cntRating(allid);
//        Long tdsn = dsnRepo.cntDesign(allid);
//        Set<DesignD> ddesigns = new HashSet<>();
//        ddesigns = dsnRepo.findAllByDesigner_Id(allid);
////        System.out.println(ddesigns);
//        Set<Long> ct = new HashSet<>();
//        Set<Long> pct = new HashSet<>();
//        for(DesignD d : ddesigns){
//            long dcode= d.getDesign_Code();
//            System.out.println(dcode);
//            ct = dsnRepo.findByContests_Design_Code(dcode);
//            for (Long ctt: ct){
//                pct.add(ctt.longValue());
//            }
//        }
//        List<Integer> ctgrcd = dsrRepo.findCategories(allid);
//        Set<CategoryD> ctgr = new HashSet<>();
//        for (Integer i:ctgrcd){
//            CategoryD e = ctgrRepo.getOne(i);
//            ctgr.add(e);
//        }
//        DesignerD mdsr = dsrRepo.getOne(designerId);
//        m.addAttribute("vote",rate);
//        m.addAttribute("ctgrdesiger",ctgr);
//        m.addAttribute("atncst",pct.size());
//        m.addAttribute("totalDesigns",tdsn);
//        m.addAttribute("adesigner", dsr);
//        m.addAttribute("propic",base64Image);
//        m.addAttribute("designer",mdsr);
//        return "/Raisa/OtherDesigners.html";
//    }
//
//    @GetMapping("/contestDetails/{designerId}/{cId}")
//    public String ContestDetails(@PathVariable("designerId") Long designerId,@PathVariable("cId") Long cId,Model m){
//        DesignerD d= dsrRepo.getOne(designerId);
//        ContestD c= cntstRepo.getOne(cId);
//        m.addAttribute("designer",d);
//        m.addAttribute("contest",c);
//        return "/Raisa/contestDetails.html";
//    }
//
//    @GetMapping("/mydesign/{designerId}")
//    public String mydesign(@PathVariable("designerId") Long designerId,Model m){
//        DesignerD dsr= dsrRepo.getOne(designerId);
//        Set<DesignD> dsns= dsnRepo.findAllByDesigner_Id(designerId);
//        Map<Long,Map<String,String>> ppData = new HashMap<>();
//        for (DesignD d:dsns){
//            byte[] pp = d.getContent();
////            System.out.println(pp);
//            String base64Image = Base64.encodeBase64String(pp);
//            System.out.println(base64Image);
//            Optional<MediaType> mediaType = determineMediaType(base64Image);
////            System.out.println(mediaType);
//            Map<String,String> temp = new HashMap<>();
//            temp.put("base64",base64Image);
//            temp.put("format",mediaType.toString());
//            ppData.put(d.getDesign_Code(),temp);
//        }
//        m.addAttribute("designer",dsr);
//        m.addAttribute("content",ppData);
//        m.addAttribute("DSN",dsns);
//        return "/Raisa/my design.html";
//    }
//
//    @GetMapping("/design/{designerId}/{DesignCode}")
//    public String onedesign(@PathVariable("designerId") Long designerId,@PathVariable("DesignCode") Long DesignCode,Model model)
//    {
//        DesignD design = dsnRepo.getOne(DesignCode);
//        DesignerD designer = dsrRepo.getOne(designerId);
//        byte[] pp = design.getContent();
////            System.out.println(pp);
//        String base64Image = Base64.encodeBase64String(pp);
//        System.out.println(base64Image);
//        Optional<MediaType> mediaType = determineMediaType(base64Image);
////            System.out.println(mediaType);
//        Map<String,String> temp = new HashMap<>();
//        temp.put("base64",base64Image);
//        temp.put("format",mediaType.toString());
//        CategoryD ctgr = ctgrRepo.getOne(design.getCtgr_Code());
//        model.addAttribute("ctgr",ctgr);
//        model.addAttribute("temp",temp);
//        model.addAttribute("design",design);
//        model.addAttribute("designer",designer);
//        return "/Raisa/oneDesign.html";
//    }
//
//    @GetMapping("/updateProfile/{designerId}")
//    public String newupdateProfile(@PathVariable("designerId") long designerId,Model model)
//    {
//        DesignerD dsr = dsrRepo.getOne(designerId);
//        //System.out.println("category " + designerId);
//        //System.out.println(ctr);
//        model.addAttribute("designer",dsr);
//        return "/Raisa/updateProfile.html";
//    }
//
//    @PostMapping("/update/{designerId}")
//    public String updated(@PathVariable("designerId") long designerId,
//                          @RequestParam(value = "Dname") String Dname,
//                          @RequestParam(value = "oldpas") String oldpas,
//                          @RequestParam(value = "newpas") String newpas,
//                          @RequestParam(value = "email") String email,
//                          @RequestParam(value = "contact") String contact,
//                          @RequestParam(value = "address") String address,
//                          @RequestParam(value = "bio") String bio,
//                          @RequestParam(name = "dob", required = false) LocalDate dob,
//                          @RequestParam(value = "propic") MultipartFile propic,
//                          Model model)
//    {
//        DesignerD dsr = dsrRepo.getOne(designerId);
//        System.out.println(Dname);
//        if(dsr.getPassword().equals(oldpas)) {
//            if(!Dname.isEmpty()) {
//                dsr.setDesignerName(Dname);
//            }
//            if(!newpas.isEmpty()){
//                dsr.setPassword(newpas);
//            }
//            if(!email.isEmpty()){
//                dsr.setEmailAddress(email);
//            }
//            if(!contact.isEmpty()){
//                dsr.setContactNo(contact);
//            }
//            if(!address.isEmpty()){
//                dsr.setAddress(address);
//            }
//            if(dob!=null){
//                dsr.setDateOfBirth(dob);
//            }
//            if(!bio.isEmpty()){
//                dsr.setBio(bio);
//            }
//            try {
//                if (!propic.isEmpty()) {
//                    byte[] bcont = propic.getBytes();
//                    dsr.setProfilePhoto(bcont);
//                    // Now you have the content of the file as a byte array (fileBytes)
//                    // Your handling logic using the byte array
//                }
//            } catch (IOException e) {
//                // Handle the IOException
//                return "Failed to read the file.";
//            }
//        }
//        dsrRepo.save(dsr);
//        return "redirect:/DesignerProfile/{designerId}";
//    }
//
//    @GetMapping("/seeadmins/{designerId}")
//    public String seeadmins(@PathVariable("designerId") long designerId,Model m)
//    {
//        List<AdminD> alladm = admRepo.findAll();
//        DesignerD d= dsrRepo.getOne(designerId);
//        m.addAttribute("alladm",alladm);
//        m.addAttribute("designer",d);
//        return "/Raisa/seeAdmins.html";
//    }
//
//    @GetMapping("/AdminDetails/{designerId}/{allid}")
//    public String AdminDetails(@PathVariable(value = "designerId") long designerId,@PathVariable(value = "allid") long allid, Model m){
//        AdminD adm = admRepo.getOne(allid);
//        Long cntno = cntstRepo.contestHolded(adm.getAdmin_Id());
//        DesignerD mdsr = dsrRepo.getOne(designerId);
//        m.addAttribute("admin",adm);
//        m.addAttribute("designer",mdsr);
//        m.addAttribute("hold",cntno);
//        return "/Raisa/Admin_Details.html";
//    }
//
//    @GetMapping("/seeusers/{designerId}")
//    public String seeusers(@PathVariable("designerId") long designerId,Model m)
//    {
//        List<UserD> allusr = usrRepo.findAll();
//        System.out.println(allusr);
//        DesignerD d= dsrRepo.getOne(designerId);
//        m.addAttribute("allusr",allusr);
//        m.addAttribute("designer",d);
//        return "/Raisa/seeUsers.html";
//    }
//
//    @GetMapping("/UserDetails/{designerId}/{allid}")
//    public String UserDetails(@PathVariable(value = "designerId") long designerId,@PathVariable(value = "allid") long allid, Model m){
//        UserD usr = usrRepo.getOne(allid);
//        DesignerD mdsr = dsrRepo.getOne(designerId);
//        m.addAttribute("user",usr);
//        m.addAttribute("designer",mdsr);
//        return "/Raisa/User_Details.html";
//    }
//
//    @GetMapping("/seeAnnouncement/{designerId}")
//    public String seeAnnouncement(@PathVariable(value = "designerId") long designerId,Model model)
//    {
//        List<Announcement> ann = annRepo.findAll();
//        DesignerD dsn = dsrRepo.getOne(designerId);
//        Collections.sort(ann, Comparator.comparing(Announcement::getDate).reversed());
//        Map<Long,String> admname = new HashMap<>();
//        for(Announcement a:ann){
//            Long adm  = a.getAdmin_Id();
//            AdminD adname  = admRepo.getOne(adm);
//            admname.put(a.getAnc_Code(),adname.getAdmin_Name());
//            System.out.println(admname);
//            System.out.println(a.getDescription());
//        }
//        model.addAttribute("annoucements",ann);
//        model.addAttribute("adname",admname);
//        model.addAttribute("designer",dsn);
//        return "/Raisa/Announcement.html";
//    }
////    @GetMapping("/message/{designerId}")
////    public String message(@PathVariable(value = "designerId") long designerId, Model m){
////        DesignerD mdsr = dsrRepo.getOne(designerId);
////        m.addAttribute("designer",mdsr);
////        return "message.html";
////    }
//
//    // Method to determine media type based on file content
//    private Optional<MediaType> determineMediaType(String fileContent) {
//        // Use MediaTypeFactory to determine media type based on file content
//        Optional<MediaType> mediaType = MediaTypeFactory.getMediaType(fileContent);
//        return mediaType;
//    }
//}
//
