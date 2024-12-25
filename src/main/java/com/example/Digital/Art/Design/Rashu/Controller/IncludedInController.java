package com.example.Digital.Art.Design.Rashu.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Digital.Art.Design.Rashu.EntityClasses.*;
import com.example.Digital.Art.Design.Rashu.Repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/included-in")
public class IncludedInController {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @PostMapping("/update-selection")
    public void updateSelection(@RequestBody UpdateSelectionRequest request) {
        Rashu_IncludedIn includedIn = request.getIncludedIn();
        Integer selection = request.getSelection();

        // Ensure that the includedIn entity is managed by the EntityManager
        if (!entityManager.contains(includedIn)) {
            includedIn = entityManager.merge(includedIn);
        }

        includedIn.setSelection(selection);
    }
}