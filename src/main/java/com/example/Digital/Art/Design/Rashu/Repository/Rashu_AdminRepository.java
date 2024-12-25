package com.example.Digital.Art.Design.Rashu.Repository;

import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Rashu_AdminRepository extends JpaRepository<Rashu_Admin, Long> {
    // You can add custom query methods here if needed
    Rashu_Admin findByEmail(String email);
    Rashu_Admin findByIdAndPass(Long id, String pass);
}
