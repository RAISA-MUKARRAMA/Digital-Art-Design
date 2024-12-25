package com.example.Digital.Art.Design.Rashu.Repository;

import com.example.Digital.Art.Design.Rashu.EntityClasses.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface Rashu_ContestRepository extends JpaRepository<Rashu_Contest, Long> {
    // You can add custom query methods here if needed
    List<Rashu_Contest> findByAdminId(Long AdminId);

}
