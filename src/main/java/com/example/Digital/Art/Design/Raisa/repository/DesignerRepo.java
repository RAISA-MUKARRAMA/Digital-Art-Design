package com.example.Digital.Art.Design.Raisa.repository;

import com.example.Digital.Art.Design.Raisa.model.CategoryD;
import com.example.Digital.Art.Design.Raisa.model.DesignerD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignerRepo extends JpaRepository<DesignerD,Long> {
    @Query(value = "SELECT Ctgr_code FROM `works for` WHERE designer_id= :id",nativeQuery = true)
    List<Integer> findCategories(@Param("id") long id);


//    @Query(value = "INSERT INTO `works for` VALUES(:sctgr,:designerId)")
//    void saveinworksfor(@Param("sctgr") int sctgr,@Param("designerId") long designerId);
}
