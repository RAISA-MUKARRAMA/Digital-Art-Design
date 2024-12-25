package com.example.Digital.Art.Design.Arupa.Repository;
import com.example.Digital.Art.Design.Arupa.EntityClasses.UserUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserUser, Long> {
    // You can add custom query methods here if needed
    UserUser findByEmail(String email);
}