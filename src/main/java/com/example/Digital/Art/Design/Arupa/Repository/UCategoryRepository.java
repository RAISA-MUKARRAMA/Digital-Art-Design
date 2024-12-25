package com.example.Digital.Art.Design.Arupa.Repository;

import com.example.Digital.Art.Design.Arupa.EntityClasses.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UCategoryRepository extends JpaRepository<UserCategory, Integer> {
    public UserCategory findBycName(String category);
}
