package com.example.Digital.Art.Design.Rashu.Repository;

import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Rashu_AnnouncementRepository extends JpaRepository<Rashu_Announcement, Long> {
    // You can add custom query methods here if needed
    List<Rashu_Announcement> findByAdminId(Long AdminId);
}
