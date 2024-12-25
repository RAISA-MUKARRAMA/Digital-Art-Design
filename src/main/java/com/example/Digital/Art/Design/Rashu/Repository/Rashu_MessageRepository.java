package com.example.Digital.Art.Design.Rashu.Repository;

import com.example.Digital.Art.Design.Rashu.EntityClasses.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface Rashu_MessageRepository extends JpaRepository<Rashu_Message, LocalDateTime> {
    // You can add custom query methods here if needed
    List<Rashu_Message> findByAdminId(Long AdminId);
}