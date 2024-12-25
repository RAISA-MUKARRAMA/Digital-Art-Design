package com.example.Digital.Art.Design.Rashu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.example.Digital.Art.Design.Rashu.EntityClasses.*;
import com.example.Digital.Art.Design.Rashu.Repository.*;

import java.util.*;
import java.time.format.DateTimeFormatter;
@Transactional
@RestController
@RequestMapping("/Rashu_request")
public class Rashu_RequestToController {
    @Autowired
    private Rashu_RequestToRepository requestToRepository;

    @GetMapping("/list")
    public ResponseEntity<List<RequestWithFormattedDate>> getRequestList(@RequestParam("admin_id") Long admin_id) {
        List<Rashu_RequestTo> requestList = requestToRepository.findByAdminId(admin_id);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<RequestWithFormattedDate> requestWithFormattedDateList = new ArrayList<>();
        // Format the LocalDateTime in each announcement
        for (Rashu_RequestTo request : requestList) {
            String formattedDate = request.getDate().format(formatter);

            RequestWithFormattedDate requestWithFormattedDate = new RequestWithFormattedDate(request, formattedDate);
            System.out.println(requestWithFormattedDate.getFormattedDate());
            requestWithFormattedDateList.add(requestWithFormattedDate);
        }

        // Sort the list in descending order based on formattedDate
        //requestWithFormattedDateList.sort(Comparator.comparing(RequestWithFormattedDate::getFormattedDate).reversed());

        return ResponseEntity.ok(requestWithFormattedDateList);
    }
}
