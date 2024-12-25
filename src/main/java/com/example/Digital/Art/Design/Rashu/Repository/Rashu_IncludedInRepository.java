package com.example.Digital.Art.Design.Rashu.Repository;

import com.example.Digital.Art.Design.Rashu.EntityClasses.*;
import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_IncludedInId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Rashu_IncludedInRepository extends JpaRepository<Rashu_IncludedIn, Rashu_IncludedInId> {
    List<Rashu_IncludedIn> findByContest(Rashu_Contest contest);
}
