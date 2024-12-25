package com.example.Digital.Art.Design.Rashu.Repository;
import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_Designer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Rashu_DesignerRepository extends JpaRepository<Rashu_Designer, Long> {
        // You can add custom query methods here if needed
        Rashu_Designer findByEmail(String email);

}
