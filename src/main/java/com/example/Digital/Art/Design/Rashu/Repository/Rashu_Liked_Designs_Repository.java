package com.example.Digital.Art.Design.Rashu.Repository;
import com.example.Digital.Art.Design.Rashu.EntityClasses.*;
import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_IncludedInId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface Rashu_Liked_Designs_Repository  extends JpaRepository<Rashu_Design, Long> {
    @Query("SELECT rld.user FROM Rashu_Liked_Designs rld WHERE rld.design.designCode = :designCode")
    List<Rashu_Liked_Designs> findByDesign_DesignCode(Integer designCode);
}
