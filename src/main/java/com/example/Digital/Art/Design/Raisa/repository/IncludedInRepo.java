package com.example.Digital.Art.Design.Raisa.repository;

import com.example.Digital.Art.Design.Raisa.model.IncludedInD;
import com.example.Digital.Art.Design.Raisa.model.IncludedInIdD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncludedInRepo extends JpaRepository<IncludedInD, IncludedInIdD> {
}
