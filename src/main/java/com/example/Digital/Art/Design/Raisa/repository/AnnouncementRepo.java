package com.example.Digital.Art.Design.Raisa.repository;

import com.example.Digital.Art.Design.Raisa.model.AnnouncementD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepo extends JpaRepository<AnnouncementD,Long> {
}
