package com.example.Digital.Art.Design.Rashu.Repository;

import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface Rashu_CategoryRepository extends JpaRepository<Rashu_Category, Integer > {
    // You can add custom query methods here if needed
    List<Rashu_Category> findAll();

}