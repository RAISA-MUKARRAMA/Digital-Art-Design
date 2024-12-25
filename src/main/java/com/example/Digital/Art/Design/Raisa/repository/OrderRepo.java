package com.example.Digital.Art.Design.Raisa.repository;

import com.example.Digital.Art.Design.Raisa.model.OrderD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepo extends JpaRepository<OrderD,Long> {
    @Query(value = "SELECT * FROM `order` WHERE Designer_Id= :id ORDER BY Date DESC",nativeQuery = true)
    List<OrderD> findByDesignerId(@Param("id") long id);

    @Query(value = "SELECT COUNT(order_code) FROM `order` WHERE user_id=:userId",nativeQuery = true)

    Long designOrdered(@Param("userId") long userId);
}
