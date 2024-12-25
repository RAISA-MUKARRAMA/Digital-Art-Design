package com.example.Digital.Art.Design.Raisa.repository;

import com.example.Digital.Art.Design.Raisa.model.CategoryD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepo extends JpaRepository<CategoryD,Integer> {

}
