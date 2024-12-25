package com.example.Digital.Art.Design.Arupa.Repository;

import com.example.Digital.Art.Design.Arupa.EntityClasses.UserContest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserContestRepository extends JpaRepository<UserContest, Long> {
    public List<UserContest> findByuserId(Long  userId);
}
