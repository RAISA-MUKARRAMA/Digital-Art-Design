package com.example.Digital.Art.Design.Rashu.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_Design;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface Rashu_DesignRepository extends JpaRepository<Rashu_Design, Long> {
    Optional<Rashu_Design> findByDesignerId(Long designerId);

    List<Rashu_Design> findByDesignCodeIn(List<Long> designCodes);

    List<Rashu_Design> findAllByDesignerId(Long designerId);

    // Use a custom query to fetch designs based on the contest ID
    @Query("SELECT d FROM Rashu_Design d JOIN Rashu_IncludedIn i ON d.designCode = i.design.designCode WHERE i.contest.cnt_code = :contestId")
    List<Rashu_Design> findDesignsByContestId(@Param("contestId") Long contestId);
}
