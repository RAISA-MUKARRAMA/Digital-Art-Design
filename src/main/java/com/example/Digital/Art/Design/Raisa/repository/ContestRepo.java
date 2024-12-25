package com.example.Digital.Art.Design.Raisa.repository;

import com.example.Digital.Art.Design.Raisa.model.ContestD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContestRepo extends JpaRepository<ContestD,Long> {
    @Query(value = "SELECT * FROM contest WHERE Last_Date_of_Sub>=CURRENT_TIME AND Starting_Date<=CURRENT_TIME", nativeQuery = true)
    List<ContestD> findRunning();

    @Query(value = "SELECT * FROM contest WHERE Last_Date_of_Sub<CURRENT_TIME AND Starting_Date<=CURRENT_TIME", nativeQuery = true)
    List<ContestD> findPast();

    @Query(value = "SELECT * FROM contest WHERE Last_Date_of_Sub>=CURRENT_TIME AND Starting_Date>=CURRENT_TIME", nativeQuery = true)
    List<ContestD> findUpcoming();

    @Query(value = "SELECT COUNT(Contest_Code) FROM contest WHERE Admin_Id=:adminId",nativeQuery = true)

    Long contestHolded(@Param("adminId") long adminId);

    @Query(value = "SELECT COUNT(Contest_Code) FROM contest WHERE `User ID`=:userId",nativeQuery = true)

    Long contestRequested(@Param("userId") long userId);
}
