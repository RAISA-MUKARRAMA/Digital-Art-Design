
package com.example.Digital.Art.Design.Rashu.Service;

import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_Admin;
import com.example.Digital.Art.Design.Rashu.Repository.Rashu_AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminProfileService {

    @Autowired
    private Rashu_AdminRepository adminRepository;

    public Rashu_Admin getAdminProfile(Long adminId) {
        return adminRepository.findById(adminId).orElse(null);
    }
}
