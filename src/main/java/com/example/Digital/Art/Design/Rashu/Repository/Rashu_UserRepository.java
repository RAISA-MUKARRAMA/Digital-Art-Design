package com.example.Digital.Art.Design.Rashu.Repository;
import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Rashu_UserRepository extends JpaRepository<Rashu_User, Long> {
    // You can add custom query methods here if needed
    Rashu_User findByEmail(String email);
}