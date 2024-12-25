package com.example.Digital.Art.Design.Arupa.Repository;
import com.example.Digital.Art.Design.Arupa.EntityClasses.UserDesigner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignerRepository extends JpaRepository<UserDesigner, Long> {
        // You can add custom query methods here if needed
        UserDesigner findByEmail(String email);
}
