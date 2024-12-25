package com.example.Digital.Art.Design.Rashu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.example.Digital.Art.Design.Rashu.EntityClasses.*;
import com.example.Digital.Art.Design.Rashu.Repository.*;

import java.util.*;
import java.time.format.DateTimeFormatter;

@Transactional
@RestController
@RequestMapping("/Rashu_announcement")
public class Rashu_SeeAnnouncementController {
    @Autowired
    private Rashu_AnnouncementRepository announcementRepository;

    @GetMapping("/list")
    public ResponseEntity<List<AnnouncementWithFormattedDate>> getAnnouncementList(@RequestParam("adminId") Long adminId) {
        List<Rashu_Announcement> announcementList = announcementRepository.findByAdminId(adminId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<AnnouncementWithFormattedDate> announcementWithFormattedDateList = new ArrayList<>();

        for (Rashu_Announcement announcement : announcementList) {
            String formattedDate = announcement.getDate().format(formatter);
            System.out.println(formattedDate);
            AnnouncementWithFormattedDate announcementWithFormattedDate = new AnnouncementWithFormattedDate(announcement, formattedDate);

            announcementWithFormattedDateList.add(announcementWithFormattedDate);
        }

        announcementWithFormattedDateList.sort(Comparator.comparing(AnnouncementWithFormattedDate::getFormattedDate).reversed());
        return ResponseEntity.ok(announcementWithFormattedDateList);
    }

    @GetMapping("/list/all")
    public ResponseEntity<List<AnnouncementWithFormattedDate>> getAllAnnouncements() {
        List<Rashu_Announcement> announcementList = announcementRepository.findAll();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<AnnouncementWithFormattedDate> announcementWithFormattedDateList = new ArrayList<>();

        for (Rashu_Announcement announcement : announcementList) {
            String formattedDate = announcement.getDate().format(formatter);
            AnnouncementWithFormattedDate announcementWithFormattedDate = new AnnouncementWithFormattedDate(announcement, formattedDate);
            announcementWithFormattedDateList.add(announcementWithFormattedDate);
        }

        announcementWithFormattedDateList.sort(Comparator.comparing(AnnouncementWithFormattedDate::getFormattedDate).reversed());

        return ResponseEntity.ok(announcementWithFormattedDateList);
    }
}
