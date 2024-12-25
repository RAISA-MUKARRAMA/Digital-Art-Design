package com.example.Digital.Art.Design.Rashu.Controller;

import org.aspectj.bridge.Message;
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
@RequestMapping("/Rashu_Message")
public class Rashu_MessageController {
    @Autowired
    private Rashu_MessageRepository messageRepository;

    @GetMapping("/list")
    public ResponseEntity<List<MessageWithFormattedDate>> getAnnouncementList(@RequestParam("adminId") Long adminId) {
        List<Rashu_Message> messageList = messageRepository.findByAdminId(adminId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<MessageWithFormattedDate> messageWithFormattedDateList = new ArrayList<>();
        // Format the LocalDateTime in each announcement
        for (Rashu_Message message : messageList) {
            String formattedDate = message.getDate().format(formatter);

            MessageWithFormattedDate messageWithFormattedDate = new MessageWithFormattedDate(message, formattedDate);
            System.out.println(messageWithFormattedDate.getFormattedDate());
            messageWithFormattedDateList.add(messageWithFormattedDate);
        }

        return ResponseEntity.ok(messageWithFormattedDateList);
    }
}
