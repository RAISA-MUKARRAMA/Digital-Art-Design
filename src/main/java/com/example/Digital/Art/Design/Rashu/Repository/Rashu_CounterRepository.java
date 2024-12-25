package com.example.Digital.Art.Design.Rashu.Repository;
import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Rashu_CounterRepository extends JpaRepository<Rashu_Counter, Long> {

}

