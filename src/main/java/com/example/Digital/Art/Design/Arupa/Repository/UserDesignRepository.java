package com.example.Digital.Art.Design.Arupa.Repository;

import com.example.Digital.Art.Design.Arupa.EntityClasses.Udesign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDesignRepository extends JpaRepository<Udesign,Long> {

    @Query(value = "SELECT * FROM `design` WHERE designer_id= :designerId",nativeQuery = true)
    public List<Udesign> findAllBydesigner_id(Long designerId);
}