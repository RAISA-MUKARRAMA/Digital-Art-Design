package com.example.Digital.Art.Design.Arupa.Repository;

import com.example.Digital.Art.Design.Arupa.EntityClasses.UserAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<UserAdmin, Long> {
    // You can add custom query methods here if needed
    UserAdmin findByEmail(String email);
    UserAdmin findByIdAndPass(Long id, String pass);
}
