package com.example.Digital.Art.Design.Rashu.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Digital.Art.Design.Rashu.EntityClasses.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
public interface Rashu_Order_Repository extends JpaRepository<Rashu_Order, Long>{
    List<Rashu_Order> findAllByUserId(Long userid);
}
