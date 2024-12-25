package com.example.Digital.Art.Design.Raisa.repository;

import com.example.Digital.Art.Design.Raisa.model.DesignD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface DesignRepo extends JpaRepository<DesignD,Long> {


    @Query(value = "SELECT Design_Code FROM `included_in` WHERE Contest_Code= :contestCode",nativeQuery = true)
    List<Long> findByContests_Contest_Code(@Param("contestCode") long contestCode);

    @Query(value = "SELECT SUM(rating) FROM `design` WHERE Designer_Id= :id",nativeQuery = true)
    Long cntRating(@Param("id") long id);

    @Query(value = "SELECT COUNT(Designer_Id) FROM `design` WHERE Designer_Id= :id",nativeQuery = true)
    Long cntDesign(@Param("id") long id);
    @Query(value = "SELECT * FROM `design` WHERE Designer_Id= :id",nativeQuery = true)
    Set<DesignD> findAllByDesigner_Id(@Param("id") long id);
    @Query(value = "SELECT Contest_Code FROM `included_in` WHERE Design_Code= :designCode",nativeQuery = true)
    Set<Long> findByContests_Design_Code(@Param("designCode") long designCode);

    @Query(value = "SELECT design_code,selection  FROM `included_in` WHERE Contest_Code= :contestCode",nativeQuery = true)
    List<Pair<Long,Integer>> findByContests_Design_Code_Selection(@Param("contestCode") long contestCode);
}
